package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.data.domain.entity.AccountItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.AccountItemDetailedInformationRepository;
import com.cornerfoodmarketwebsite.data.domain.repository.AccountItemInformationRepository;
import com.cornerfoodmarketwebsite.data.utils.ItemIdAndQuantity;
import com.cornerfoodmarketwebsite.business.dto.request.domain.AddToCartItem;
import com.cornerfoodmarketwebsite.business.dto.request.domain.CustomUserDetails;
import com.cornerfoodmarketwebsite.business.dto.response.InCartResponse;
import com.cornerfoodmarketwebsite.business.dto.response.utils.AddToCartResponse;
import com.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.QAccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.utils.ShoppingCartItemsList;
import com.cornerfoodmarketwebsite.data.single_table.entity.CartItem;
import com.cornerfoodmarketwebsite.data.single_table.entity.Customer;
import com.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;
import com.cornerfoodmarketwebsite.data.single_table.repository.CartItemRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.CustomerRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AccountItemInformationService {
    private final AccountItemInformationRepository accountItemInformationRepository;
    private final AccountItemDetailedInformationRepository accountItemDetailedInformationRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public AccountItemInformationService(AccountItemInformationRepository accountItemInformationRepository,
                                         AccountItemDetailedInformationRepository accountItemDetailedInformationRepository,
                                         CustomerRepository customerRepository, CartItemRepository cartItemRepository,
                                         ItemRepository itemRepository) {
        this.accountItemInformationRepository = accountItemInformationRepository;
        this.accountItemDetailedInformationRepository = accountItemDetailedInformationRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
    }

    public List<List<AccountItemInformation>> getItemsInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        short customerId = this.customerRepository.getIdByEmail(((CustomUserDetails) auth.getPrincipal()).getUsername());

//        //Obtain the entity manager for the current transaction
//        EntityManagerHolder holder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory);
//        EntityManager entityManager = holder.getEntityManager();
//
//        //Unwrap to get the underlying hibernate session
//        Session hibernateSession = entityManager.unwrap(Session.class);
//
//        // Enable filter and set parameter
//        Filter filter = hibernateSession.enableFilter("filterByCustomerId");
//        filter.setParameter("customerId", customerId);
//        filter.validate();

//        Iterable<AccountItemInformation> accountItemInformationList2 = entityManager.createQuery(
//                "SELECT I1 FROM AccountItemInformation I1 LEFT JOIN I1.cartItem CI1 WITH CI1.itemId = I1.id AND CI1.customerId = " +
//                        customerId + " WHERE I1.isOnSale = true and I1.quantity > 0", AccountItemInformation.class).getResultList();

        QAccountItemInformation qAccountItemInformation = QAccountItemInformation.accountItemInformation;

        Iterable<AccountItemInformation> accountItemInformationList = this.accountItemInformationRepository.findAllOnSale(customerId);

        List<List<AccountItemInformation>> accountItemInformationLists = new ArrayList<>();

        AtomicInteger listNumber = new AtomicInteger(-1);
        AtomicInteger itemNumber = new AtomicInteger();
        accountItemInformationList.forEach(accountItemInformation -> {
            if ((itemNumber.get() % 4) == 0) {
                accountItemInformationLists.add(new ArrayList<>());
                listNumber.getAndIncrement();
            }

            accountItemInformationLists.get(listNumber.get()).add(accountItemInformation);
            itemNumber.getAndIncrement();
        });

        return accountItemInformationLists;
    }

    public AccountItemDetailedInformation getItemDetailedInformation(String itemSku) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        short customerId = this.customerRepository.getIdByEmail(((CustomUserDetails) auth.getPrincipal()).getUsername());
        AccountItemDetailedInformation accountItemDetailedInformation = this.accountItemDetailedInformationRepository.findBySku(itemSku, customerId);

        return accountItemDetailedInformation;
    }

    public InCartResponse addItemToCart(AddToCartItem addToCartItem) {
        Customer customer = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCustomer();

        short customerId = this.customerRepository.getIdByEmail(customer.getEmail());
        String requestedItemSku = addToCartItem.getSku();
        String addToCartResponse = null;
        boolean isSuccessfulResponse = false;

        List<ItemIdAndQuantity> itemIdAndQuantityList = this.itemRepository.getIdAndQuantityBySku(requestedItemSku);
        if (itemIdAndQuantityList.size() > 1) {         // If there is more than one record in the result set (with the same sku), return server error.
            addToCartResponse = AddToCartResponse.SERVER_ERROR.getAddToCartMessage();
        }
        short itemId = itemIdAndQuantityList.get(0).getId();
        short itemQuantity = itemIdAndQuantityList.get(0).getQuantity();
        short requestedQuantity = addToCartItem.getQuantity();

        CartItem cartItem;
        CartItemId cartItemId = new CartItemId(customerId, itemId);

        if (this.cartItemRepository.existsById(cartItemId)) {
            cartItem = this.cartItemRepository.getById(cartItemId);
        } else {
            cartItem = null;
        }

        short amountInCart = ((cartItem == null) ? 0 : cartItem.getInCartQuantity());
        short totalRequestedAmount = (short) (requestedQuantity + amountInCart);
        short totalItemsToAdd = 0;

        if (itemQuantity == 0) {
            addToCartResponse = AddToCartResponse.NOT_AVAILABLE.getAddToCartMessage();
        } else if (itemQuantity < totalRequestedAmount) {
            addToCartResponse = AddToCartResponse.NOT_ENOUGH.getAddToCartMessage();
            if (cartItem == null) {
                totalItemsToAdd = itemQuantity;
                cartItem = new CartItem(customerId, itemId, itemQuantity);
            } else {
                totalItemsToAdd += (itemQuantity - amountInCart);
                cartItem.setInCartQuantity(itemQuantity);
            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem(customerId, itemId, requestedQuantity);
            } else {
                cartItem.setInCartQuantity(totalRequestedAmount);
            }
            totalItemsToAdd = requestedQuantity;
        }

        try {
            this.cartItemRepository.save(cartItem);
            if (addToCartResponse == null) {
                addToCartResponse =  AddToCartResponse.SUCCESS.getAddToCartMessage();
                isSuccessfulResponse = true;
            }
            customer.addNumberOfItemsToCart(totalItemsToAdd);
            this.customerRepository.save(customer);
        } catch (Exception ex) {
            // TODO - Log the exception message.
            addToCartResponse = AddToCartResponse.SERVER_ERROR.getAddToCartMessage();
        }

        Short customerCartRequestedItemTotal = this.cartItemRepository.getCustomerRequestedItemTotal(customerId, itemId);
        short customerCartTotalItems = customer.getTotalCartItems();

        return new InCartResponse(addToCartResponse, (customerCartRequestedItemTotal == null ? 0: customerCartRequestedItemTotal),
                customerCartTotalItems, requestedItemSku, isSuccessfulResponse);
    }

    public ShoppingCartItemsList getAllInCartItems() {
        Customer customer = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCustomer();

        short customerId = this.customerRepository.getIdByEmail(customer.getEmail());

        ShoppingCartItemsList accountInCartItemInformationList = this.accountItemInformationRepository.findAllInCart(customerId);
        accountInCartItemInformationList.setShoppingCartTotals();

        return accountInCartItemInformationList;
    }

}

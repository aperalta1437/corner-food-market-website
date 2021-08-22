package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.AddToCartItem;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.CustomUserDetails;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.InCartResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.utils.AddToCartResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.AccountItemDetailedInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository.AccountItemDetailedInformationRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository.AccountItemInformationRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.CartItem;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.CartItemRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.CustomerRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.ItemRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.utils.ItemIdAndQuantity;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        short customerId = this.customerRepository.getIdByEmail(((CustomUserDetails) auth.getPrincipal()).getUsername());
        String requestedItemSku = addToCartItem.getSku();
        String addToCartResponse = null;
        boolean isSuccessfulResponse = false;

        List<ItemIdAndQuantity> itemIdAndQuantityList = this.itemRepository.getIdAndQuantityBySku(requestedItemSku);
        if (itemIdAndQuantityList.size() > 1) {         // If there is more than one record in the result set, return server error.
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

        short totalRequestedAmount = (short) (requestedQuantity + ((cartItem == null) ? 0 : cartItem.getQuantity()));

        if (itemQuantity == 0) {
            addToCartResponse = AddToCartResponse.NOT_AVAILABLE.getAddToCartMessage();
        } else if (itemQuantity < totalRequestedAmount) {
            addToCartResponse = AddToCartResponse.NOT_ENOUGH.getAddToCartMessage();

            if (cartItem == null) {
                cartItem = new CartItem(customerId, itemId, itemQuantity);
            } else {
                cartItem.setQuantity(itemQuantity);
            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem(customerId, itemId, requestedQuantity);
            } else {
                cartItem.setQuantity(totalRequestedAmount);
            }
        }

        try {
            this.cartItemRepository.save(cartItem);
            if (addToCartResponse == null) {
                addToCartResponse =  AddToCartResponse.SUCCESS.getAddToCartMessage();
                isSuccessfulResponse = true;
            }
        } catch (Exception ex) {
            // TODO - Log the exception message.
            addToCartResponse = AddToCartResponse.SERVER_ERROR.getAddToCartMessage();
        }

        short customerCartRequestedItemTotal = this.cartItemRepository.getCustomerRequestedItemTotal(customerId, itemId);
        short customerCartTotalItems = this.cartItemRepository.getCustomerTotalItems(customerId);

        return new InCartResponse(addToCartResponse, customerCartRequestedItemTotal, customerCartTotalItems, requestedItemSku, isSuccessfulResponse);
    }

}

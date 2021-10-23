package com.cornerfoodmarketwebsite.data.domain.repository;

import com.cornerfoodmarketwebsite.data.domain.entity.AdministratorAccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.utils.ShoppingCartItemsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorAccountItemInformationRepository extends JpaRepository<AdministratorAccountItemInformation, Short> {
//    @Query(value = "SELECT I1 FROM AccountItemInformation I1 LEFT JOIN I1.cartItems CI1 WITH CI1.itemId = I1.id AND CI1.customerId = :customerId WHERE I1.isOnSale = true and I1.quantity > 0")
//    Iterable<AccountItemInformation> findAllOnSale(@Param("customerId") short customerId);

    @Query(nativeQuery = true, value =
            "SELECT I1.ID, I1.NAME, I1.SKU, I1.PRICE, I1.IS_ON_SALE, I1.IS_POPULAR, D1.IS_PERCENTAGE_BASED, D1.DISCOUNT_PERCENT, D1.DISCOUNT_AMOUNT, II1.QUANTITY, IC1.NAME, IC1.URL_ROUTE_NAME, CI1.CUSTOMER_ID, CI1.IN_CART_QUANTITY " +
                    "FROM ITEM I1 " +
                    "LEFT JOIN DISCOUNT D1 ON " +
                    "I1.DISCOUNT_ID = D1.ID " +
                    "LEFT JOIN ITEM_INVENTORY II1 ON " +
                    "I1.INVENTORY_ID = II1.ID " +
                    "LEFT JOIN ITEM_CATEGORY IC1 ON " +
                    "I1.CATEGORY_ID = IC1.ID " +
                    "LEFT JOIN CART_ITEM CI1 ON " +
                    "CI1.ITEM_ID = I1.ID " +
                    "WHERE I1.IS_ON_SALE = TRUE AND II1.QUANTITY > 0")
    Iterable<AdministratorAccountItemInformation> findAllOnSale();

    @Query(value = "SELECT I1 FROM AccountItemInformation I1 WHERE I1.customerId = ?1")
    ShoppingCartItemsList findAllInCart(short customerId);
}

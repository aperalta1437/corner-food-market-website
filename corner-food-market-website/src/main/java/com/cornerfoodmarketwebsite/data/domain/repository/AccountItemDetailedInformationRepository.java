package com.cornerfoodmarketwebsite.data.domain.repository;

import com.cornerfoodmarketwebsite.data.domain.entity.AccountItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountItemDetailedInformationRepository extends CustomJpaRepository<AccountItemDetailedInformation, Short> {
//    @Query(value = "SELECT I1 FROM AccountItemDetailedInformation I1 LEFT JOIN I1.cartItems CI1 WHERE I1.sku = ?1 and I1.isOnSale = true and I1.quantity > 0 and (CI1.customerId = ?2 or CI1.customerId = NULL)")
//    AccountItemDetailedInformation findBySku(String itemSku, short customerId);

    @Query(nativeQuery = true, value =
            "SELECT I1.ID, I1.NAME, I1.DESCRIPTION, I1.SKU, I1.PRICE, I1.IS_ON_SALE, I1.IS_POPULAR, II1.QUANTITY, IC1.NAME, IC1.URL_ROUTE_NAME, CI1.CUSTOMER_ID, CI1.IN_CART_QUANTITY " +
            "FROM ITEM I1 " +
                "LEFT JOIN ITEM_INVENTORY II1 ON " +
                    "I1.INVENTORY_ID = II1.ID " +
                "LEFT JOIN ITEM_CATEGORY IC1 ON " +
                    "I1.CATEGORY_ID = IC1.ID " +
                "LEFT JOIN CART_ITEM CI1 ON " +
                    "CI1.ITEM_ID = I1.ID AND CI1.CUSTOMER_ID = :customerId " +
            "WHERE I1.SKU = :itemSku AND I1.IS_ON_SALE = TRUE AND II1.QUANTITY > 0;")
    AccountItemDetailedInformation findBySku(@Param("itemSku") String itemSku, @Param("customerId") short customerId);
}

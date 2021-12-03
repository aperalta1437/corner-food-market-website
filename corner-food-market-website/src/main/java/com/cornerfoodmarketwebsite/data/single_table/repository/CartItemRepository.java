package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.CartItem;
import com.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CustomJpaRepository<CartItem, CartItemId> {
    @Query(value = "SELECT CI1.inCartQuantity FROM CartItem CI1 WHERE CI1.customerId = ?1 AND CI1.itemId = ?2")
    Short getCustomerRequestedItemTotal(short customerId, short itemId);

    @Query(value = "SELECT SUM(CI1.inCartQuantity) FROM CartItem CI1 WHERE CI1.customerId = ?1")
    Short getCustomerTotalItems(short customerId);
}

package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.CartItem;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.Item;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    @Query(value = "SELECT CI1.quantity FROM CartItem CI1 WHERE CI1.customerId = ?1 AND CI1.itemId = ?2")
    Short getCustomerRequestedItemTotal(short customerId, short itemId);

    @Query(value = "SELECT SUM(CI1.quantity) FROM CartItem CI1 WHERE CI1.customerId = ?1")
    Short getCustomerTotalItems(short customerId);
}

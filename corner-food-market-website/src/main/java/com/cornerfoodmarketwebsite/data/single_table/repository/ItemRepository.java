package com.cornerfoodmarketwebsite.data.single_table.repository;


import com.cornerfoodmarketwebsite.data.utils.ItemIdAndQuantity;
import com.cornerfoodmarketwebsite.data.single_table.entity.Item;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends CustomJpaRepository<Item, Short> {
    @Query(value = "SELECT I1 FROM Item I1 WHERE I1.isOnSale = true")
    Iterable<Item> findAllOnSale();

    @Query(value = "SELECT I1 FROM Item I1 WHERE I1.upc = ?1")
    Item findBySku(String itemUpc);

    @Query(value = "SELECT I1.id FROM Item I1 WHERE I1.upc = ?1")
    int getIdBySku(String itemUpc);

    @Query(value = "SELECT new com.cornerfoodmarketwebsite.data.utils.ItemIdAndQuantity(I1.id, I1.itemInventory.quantity) FROM Item I1 WHERE I1.upc = ?1")
    List<ItemIdAndQuantity> getIdAndQuantityBySku(String itemUpc);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Item SET isOnSale = false WHERE id = ?1 AND isOnSale = true")
    int setIsOnSaleToFalseByItemId(Short itemId);


}
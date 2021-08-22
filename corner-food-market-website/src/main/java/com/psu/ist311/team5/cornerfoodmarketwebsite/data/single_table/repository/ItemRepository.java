package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.Item;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.utils.ItemIdAndQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Short> {
    @Query(value = "SELECT I1 FROM Item I1 WHERE I1.isOnSale = true")
    Iterable<Item> findAllOnSale();

    @Query(value = "SELECT I1 FROM Item I1 WHERE I1.sku = ?1")
    Item findBySku(String itemSku);

    @Query(value = "SELECT I1.id FROM Item I1 WHERE I1.sku = ?1")
    int getIdBySku(String itemSku);

    @Query(value = "SELECT new com.psu.ist311.team5.cornerfoodmarketwebsite.data.utils.ItemIdAndQuantity(I1.id, I1.itemInventory.quantity) FROM Item I1 WHERE I1.sku = ?1")
    List<ItemIdAndQuantity> getIdAndQuantityBySku(String itemSku);
}

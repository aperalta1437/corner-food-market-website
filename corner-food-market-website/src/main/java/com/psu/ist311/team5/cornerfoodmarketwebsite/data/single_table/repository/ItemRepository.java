package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Short> {
    @Query(value = "SELECT I1 FROM Item I1 WHERE I1.isOnSale = true")
    Iterable<Item> findAllOnSale();

    @Query(value = "SELECT I1 FROM Item I1 WHERE I1.sku = ?1")
    Item findBySku(String itemSku);
}

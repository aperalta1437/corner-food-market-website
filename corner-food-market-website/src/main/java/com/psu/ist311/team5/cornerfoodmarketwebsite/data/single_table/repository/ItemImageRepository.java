package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Integer> {
    @Query(value = "SELECT II1 FROM ItemImage II1 WHERE II1.itemId = ?1 AND II1.sortNumber = 1")
    ItemImage getMainItemImageByItemId(short id);

    @Query(value = "SELECT II1 FROM ItemImage II1 WHERE II1.itemId = ?1 ORDER BY II1.sortNumber ASC")
    Iterable<ItemImage> getSortedItemImagesByItemId(short id);
}

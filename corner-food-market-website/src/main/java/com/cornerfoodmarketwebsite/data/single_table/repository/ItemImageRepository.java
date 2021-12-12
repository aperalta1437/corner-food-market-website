package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends CustomJpaRepository<ItemImage, Integer> {
    @Query(value = "SELECT II1 FROM ItemImage II1 WHERE II1.itemId = ?1 AND II1.sortNumber = 1")
    ItemImage getMainItemImageByItemId(short itemId);

    @Query(value = "SELECT II1 FROM ItemImage II1 WHERE II1.itemId = ?1 ORDER BY II1.sortNumber ASC")
    Iterable<ItemImage> getSortedItemImagesByItemId(short itemId);

    @Query(value = "SELECT II1 FROM ItemImage II1 WHERE II1.id = ?1")
    ItemImage getItemImageById(int id);

}

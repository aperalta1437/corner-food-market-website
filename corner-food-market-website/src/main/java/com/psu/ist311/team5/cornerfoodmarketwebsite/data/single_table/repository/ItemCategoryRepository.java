package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Short> {

    @Query(value = "SELECT MAX(ICH1.category_level) FROM Item_Category_Hierarchy ICH1", nativeQuery = true)
    short getHighestCategoryLevel();

    @Query(value = "SELECT MAX(CATEGORIES_PER_LEVEL.total_categories_per_level) FROM (SELECT COUNT(ICH1) AS TOTAL_CATEGORIES_PER_LEVEL FROM Item_Category_Hierarchy ICH1 GROUP BY ICH1.category_level) CATEGORIES_PER_LEVEL", nativeQuery = true)
    short getTotalCategoriesFromBiggestLevel();
}

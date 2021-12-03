package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.business.dto.response.domain.AdministratorAddItemFormItemCategoryInformation;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemCategory;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends CustomJpaRepository<ItemCategory, Short> {

//    @Query(value = "SELECT MAX(ICH1.category_level) FROM Item_Category_Hierarchy ICH1", nativeQuery = true)
//    short getHighestCategoryLevel();
//
//    @Query(value = "SELECT MAX(CATEGORIES_PER_LEVEL.total_categories_per_level) FROM (SELECT COUNT(ICH1) AS TOTAL_CATEGORIES_PER_LEVEL FROM Item_Category_Hierarchy ICH1 GROUP BY ICH1.category_level) CATEGORIES_PER_LEVEL", nativeQuery = true)
//    short getTotalCategoriesFromBiggestLevel();

    @Query(value = "SELECT new com.cornerfoodmarketwebsite.business.dto.response.domain.AdministratorAddItemFormItemCategoryInformation(IC1.id, IC1.name, IC1.description) FROM ItemCategory IC1 WHERE isLeaf = TRUE ORDER BY IC1.name")
    List<AdministratorAddItemFormItemCategoryInformation> getLeafCategories();
}

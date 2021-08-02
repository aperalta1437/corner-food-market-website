package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain.NavigationBarCategory;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    @Autowired
    public ItemCategoryService(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public List<NavigationBarCategory> getNavigationBarCategoryHierarchy() {
        short highestCategoryLevel = this.itemCategoryRepository.getHighestCategoryLevel();
        short totalCategoriesFromBiggestLevel = this.itemCategoryRepository.getTotalCategoriesFromBiggestLevel();

        return null;
    }

}

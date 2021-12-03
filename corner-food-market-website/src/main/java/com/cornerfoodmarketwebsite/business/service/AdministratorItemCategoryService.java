package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.response.domain.AdministratorAddItemFormItemCategoryInformation;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorItemCategoryService {
    private final ItemCategoryRepository itemCategoryRepository;

    @Autowired
    public AdministratorItemCategoryService(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    public List<AdministratorAddItemFormItemCategoryInformation> getAddItemFormItemCategories() {
        return this.itemCategoryRepository.getLeafCategories();
    }
}

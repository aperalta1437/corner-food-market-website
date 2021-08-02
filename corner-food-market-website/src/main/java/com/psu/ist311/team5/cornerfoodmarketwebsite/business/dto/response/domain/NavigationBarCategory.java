package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemCategory;

public class NavigationBarCategory {
    private ItemCategory itemCategory;
    private ItemCategory[] itemCategoryChildren;

    public NavigationBarCategory(ItemCategory itemCategory, ItemCategory[] itemCategoryChildren) {
        this.itemCategory = itemCategory;
        this.itemCategoryChildren = itemCategoryChildren;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemCategory[] getItemCategoryChildren() {
        return itemCategoryChildren;
    }

    public void setItemCategoryChildren(ItemCategory[] itemCategoryChildren) {
        this.itemCategoryChildren = itemCategoryChildren;
    }
}

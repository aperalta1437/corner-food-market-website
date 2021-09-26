package com.cornerfoodmarketwebsite.business.dto.response.domain;

import com.cornerfoodmarketwebsite.data.single_table.entity.ItemCategory;

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

package com.cornerfoodmarketwebsite.business.dto.response.domain;

import java.sql.Date;

public class ItemReviewInformation extends ReviewInformation{

    private String itemName;
    private String itemMainImageSourceRelativePathName;
    private String itemSku;
    private String itemCategoryName;

    public ItemReviewInformation(String customerFullName, String subjectLine, String reviewText, float starRating,
                                 Date createdAt, String itemName, String itemSku, String itemCategoryName,
                                 String mainImageRelativePath, String mainImageFileName) {
        super(customerFullName, subjectLine, reviewText, starRating, createdAt);

        this.itemName = itemName;
        this.itemSku = itemSku;
        this.itemCategoryName = itemCategoryName;
        this.itemMainImageSourceRelativePathName = mainImageRelativePath + mainImageFileName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMainImageSourceRelativePathName() {
        return itemMainImageSourceRelativePathName;
    }

    public void setItemMainImageSourceRelativePathName(String mainImageRelativePath, String mainImageFileName) {
        this.itemMainImageSourceRelativePathName = mainImageRelativePath + mainImageFileName;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }
}

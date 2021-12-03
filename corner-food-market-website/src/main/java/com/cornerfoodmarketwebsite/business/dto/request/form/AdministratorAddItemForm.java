package com.cornerfoodmarketwebsite.business.dto.request.form;

import org.springframework.web.multipart.MultipartFile;

public class AdministratorAddItemForm {
    private String itemTitle;
    private String itemDescription;
    private double itemPrice;
    private short itemCategoryId;
    private String itemSku;
    private short itemQuantity;
    private MultipartFile itemImageFile;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public short getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(short itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public short getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(short itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public MultipartFile getItemImageFile() {
        return itemImageFile;
    }

    public void setItemImageFile(MultipartFile itemImageFile) {
        this.itemImageFile = itemImageFile;
    }
}

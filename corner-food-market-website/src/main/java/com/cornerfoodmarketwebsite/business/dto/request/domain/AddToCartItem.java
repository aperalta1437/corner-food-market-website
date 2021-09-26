package com.cornerfoodmarketwebsite.business.dto.request.domain;

public class AddToCartItem {

    private byte quantity;
    private String sku;

    public byte getQuantity() {
        return quantity;
    }

    public void setQuantity(byte quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}

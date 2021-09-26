package com.cornerfoodmarketwebsite.business.dto.response;

public class InCartResponse {
    private String addToCartResponse;
    private short cartRequestedItemTotal;
    private short cartTotalItems;
    private String requestedItemSku;
    private boolean isSuccess;

    public InCartResponse(String addToCartResponse, short cartRequestedItemTotal, short cartTotalItems, String requestedItemSku, boolean isSuccess) {
        this.addToCartResponse = addToCartResponse;
        this.cartRequestedItemTotal = cartRequestedItemTotal;
        this.cartTotalItems = cartTotalItems;
        this.requestedItemSku = requestedItemSku;
        this.isSuccess = isSuccess;
    }

    public String getAddToCartResponse() {
        return addToCartResponse;
    }

    public void setAddToCartResponse(String addToCartResponse) {
        this.addToCartResponse = addToCartResponse;
    }

    public short getCartRequestedItemTotal() {
        return cartRequestedItemTotal;
    }

    public void setCartRequestedItemTotal(short cartRequestedItemTotal) {
        this.cartRequestedItemTotal = cartRequestedItemTotal;
    }

    public short getCartTotalItems() {
        return cartTotalItems;
    }

    public void setCartTotalItems(short cartTotalItems) {
        this.cartTotalItems = cartTotalItems;
    }

    public String getRequestedItemSku() {
        return requestedItemSku;
    }

    public void setRequestedItemSku(String requestedItemSku) {
        this.requestedItemSku = requestedItemSku;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}

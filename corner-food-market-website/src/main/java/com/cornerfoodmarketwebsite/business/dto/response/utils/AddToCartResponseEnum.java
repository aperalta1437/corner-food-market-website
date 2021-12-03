package com.cornerfoodmarketwebsite.business.dto.response.utils;

public enum AddToCartResponseEnum {
    SUCCESS("Added to cart!"),
    NOT_ENOUGH("The requested quantity is no longer available, so the remaining were added to the cart."),
    NOT_AVAILABLE("This item is no longer available"),
    SERVER_ERROR("An error occurred while processing your request. Please try again later. If error persist, please contact the store manager.");

    private final String addToCartMessage;

    AddToCartResponseEnum(String addToCartMessage) {
        this.addToCartMessage = addToCartMessage;
    }

    public String getAddToCartMessage() {
        return this.addToCartMessage;
    }
}

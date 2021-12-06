package com.cornerfoodmarketwebsite.business.service.utils;

public enum UuidValidationResponseEnum {
    FOUND("UUID was successfully found."),
    NOT_FOUND("UUID was not found."),
    EXPIRED("UUID has expired."),
    IS_USED("UUID was previously used."),
    IS_CANCELLED("UUID was previously cancelled.");

    private final String responseMessage;

    UuidValidationResponseEnum(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }
}

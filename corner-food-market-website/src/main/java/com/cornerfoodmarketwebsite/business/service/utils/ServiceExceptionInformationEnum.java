package com.cornerfoodmarketwebsite.business.service.utils;

public enum ServiceExceptionInformationEnum {
    NOT_PROVIDED_TFA_TYPE_EXCEPTION("The two-factor-authentication type was not provided."),
    NOT_SUPPORTED_TFA_TYPE_EXCEPTION("The provided two-factor-authentication type is not supported.");

    private final String exceptionMessage;

    ServiceExceptionInformationEnum(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}

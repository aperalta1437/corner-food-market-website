package com.cornerfoodmarketwebsite.business.service.utils;

public class NotSupportedTfaTypeException extends Exception {
    public NotSupportedTfaTypeException(String errorMessage) {
        super(errorMessage);
    }

    public NotSupportedTfaTypeException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
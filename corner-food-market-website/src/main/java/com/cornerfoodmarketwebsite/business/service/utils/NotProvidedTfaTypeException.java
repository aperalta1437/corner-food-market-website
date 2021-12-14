package com.cornerfoodmarketwebsite.business.service.utils;

public class NotProvidedTfaTypeException extends Exception{
    public NotProvidedTfaTypeException(String errorMessage) {
        super(errorMessage);
    }

    public NotProvidedTfaTypeException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}

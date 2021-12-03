package com.cornerfoodmarketwebsite.business.service.utils;

public class NotProvidedTfaTypeException extends Exception{
    public NotProvidedTfaTypeException(String errorMessage) {
        super(errorMessage);
    }
}

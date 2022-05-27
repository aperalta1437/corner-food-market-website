package com.cornerfoodmarketwebsite.business.service.utils;

public class InvalidClientDomainException extends Exception {
    public InvalidClientDomainException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidClientDomainException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
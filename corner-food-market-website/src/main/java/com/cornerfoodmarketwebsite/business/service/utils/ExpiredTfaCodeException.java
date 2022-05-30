package com.cornerfoodmarketwebsite.business.service.utils;

public class ExpiredTfaCodeException extends Exception {
    private static final String message = "The 2-factor authentication code has expired.";

    public ExpiredTfaCodeException() {
        super(message);
    }

    public ExpiredTfaCodeException(Throwable error) {
        super(message, error);
    }
}

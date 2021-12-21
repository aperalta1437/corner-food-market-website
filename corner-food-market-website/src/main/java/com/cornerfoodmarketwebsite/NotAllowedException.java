package com.cornerfoodmarketwebsite;

public class NotAllowedException extends Exception {
    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}

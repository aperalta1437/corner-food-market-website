package com.cornerfoodmarketwebsite.configuration.utils;

public class InvalidOriginPropertyException extends Exception {
    public InvalidOriginPropertyException(String property) {
        super(String.format("The property \"%s\" is not a valid property.", property));
    }

    public InvalidOriginPropertyException(String property, Throwable error) {
        super(String.format("The property \"%s\" is not a valid property.", property), error);
    }
}

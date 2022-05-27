package com.cornerfoodmarketwebsite.configuration.utils;

public class InvalidPssClientPropertyException extends Exception {
    public InvalidPssClientPropertyException(String property) {
        super(String.format("The property \"%s\" is not a valid property.", property));
    }

    public InvalidPssClientPropertyException(String property, Throwable error) {
        super(String.format("The property \"%s\" is not a valid property.", property), error);
    }
}

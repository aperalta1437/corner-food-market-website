package com.cornerfoodmarketwebsite.configuration.utils;

public class InvalidRoleException extends Exception {
    public InvalidRoleException(String role) {
        super(String.format("The role \"%s\" is not a valid role.", role));
    }

    public InvalidRoleException(String role, Throwable error) {
        super(String.format("The role \"%s\" is not a valid role.", role), error);
    }
}

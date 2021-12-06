package com.cornerfoodmarketwebsite.business.service.utils;

public enum UuidUsageEnum {
    NEW_ADMIN_REQUEST(900000);

    private final int expirationTimeInMilliseconds;

    UuidUsageEnum(int expirationTimeInMilliseconds) {
        this.expirationTimeInMilliseconds = expirationTimeInMilliseconds;
    }

    public int getExpirationTimeInMilliseconds() {
        return expirationTimeInMilliseconds;
    }
}

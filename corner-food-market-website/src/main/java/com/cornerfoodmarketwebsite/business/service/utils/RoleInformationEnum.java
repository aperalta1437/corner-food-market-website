package com.cornerfoodmarketwebsite.business.service.utils;

public enum RoleInformationEnum {
    ADMINISTRATOR(120000);

    private final int tfaExpirationTimeInMilliseconds;

    RoleInformationEnum(int tfaExpirationTimeInMilliseconds) {
        this.tfaExpirationTimeInMilliseconds = tfaExpirationTimeInMilliseconds;
    }

    public int getTfaExpirationTimeInMilliseconds() {
        return this.tfaExpirationTimeInMilliseconds;
    }
}

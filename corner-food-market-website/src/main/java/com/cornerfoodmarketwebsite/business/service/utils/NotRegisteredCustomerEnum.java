package com.cornerfoodmarketwebsite.business.service.utils;

public enum NotRegisteredCustomerEnum {
    ANONYMOUS_CUSTOMER((short) 1);

    private final short customerId;

    NotRegisteredCustomerEnum(short customerId) {
        this.customerId = customerId;
    }


    public short getCustomerId() {
        return this.customerId;
    }
}

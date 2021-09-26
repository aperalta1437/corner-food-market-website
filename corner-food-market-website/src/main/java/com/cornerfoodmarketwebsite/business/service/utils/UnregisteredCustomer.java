package com.cornerfoodmarketwebsite.business.service.utils;

public enum UnregisteredCustomer {
    ANONYMOUS_CUSTOMER((short) 1);

    private final short customerId;

    UnregisteredCustomer(short customerId) {
        this.customerId = customerId;
    }


    public short getCustomerId() {
        return this.customerId;
    }
}

package com.cornerfoodmarketwebsite.business.service.utils;

public enum RoleInformationEnum {
    ADMINISTRATOR(120000);

    private final int tfaCodeValidTimeframe;    // TODO: find a way to account for the request processing time

    RoleInformationEnum(int tfaCodeValidTimeframe) {
        this.tfaCodeValidTimeframe = tfaCodeValidTimeframe;
    }

    public int getTfaCodeValidTimeframe() {
        return this.tfaCodeValidTimeframe;
    }
}

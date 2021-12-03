package com.cornerfoodmarketwebsite.business.dto.request.domain;

public class AdministratorSecondFactorLoginFields extends AdministratorFirstFactorLoginFields {
    private String tfaCode;

    public String getTfaCode() {
        return tfaCode;
    }

    public void setTfaCode(String tfaCode) {
        this.tfaCode = tfaCode;
    }
}

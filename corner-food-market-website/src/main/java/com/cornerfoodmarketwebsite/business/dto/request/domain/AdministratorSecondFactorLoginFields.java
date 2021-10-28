package com.cornerfoodmarketwebsite.business.dto.request.domain;

public class AdministratorSecondFactorLoginFields {
    private String email;
    private String password;
    private String tfaCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTfaCode() {
        return tfaCode;
    }

    public void setTfaCode(String tfaCode) {
        this.tfaCode = tfaCode;
    }
}

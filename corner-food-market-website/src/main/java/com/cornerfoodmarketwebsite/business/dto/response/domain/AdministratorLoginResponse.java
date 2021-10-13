package com.cornerfoodmarketwebsite.business.dto.response.domain;

public class AdministratorLoginResponse {

    private final String jwt;

    public AdministratorLoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}

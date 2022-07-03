package com.cornerfoodmarketwebsite.configuration.utils;

import lombok.Getter;

import java.util.Base64;

@Getter
public class TokenProperties {
    private String secretKey;

    public void setSecretKey(String secretKey) {
        // Convert secret key to byte array
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}

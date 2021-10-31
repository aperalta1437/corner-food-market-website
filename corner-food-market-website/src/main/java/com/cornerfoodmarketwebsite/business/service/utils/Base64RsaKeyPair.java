package com.cornerfoodmarketwebsite.business.service.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Base64RsaKeyPair {
    private final String base64PrivateKey;
    private final String base64PublicKey;

    public Base64RsaKeyPair(KeyPair keyPair) {
        this.base64PrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        this.base64PublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    public String getBase64PrivateKey() {
        return base64PrivateKey;
    }

    public String getBase64PublicKey() {
        return base64PublicKey;
    }
}

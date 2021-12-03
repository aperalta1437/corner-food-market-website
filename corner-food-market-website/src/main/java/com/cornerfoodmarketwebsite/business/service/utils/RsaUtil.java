package com.cornerfoodmarketwebsite.business.service.utils;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RsaUtil {
    private final KeyPairGenerator keyPairGenerator;

    public RsaUtil() throws NoSuchAlgorithmException {
        this.keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        this.keyPairGenerator.initialize(1024);
    }

    public Base64RsaKeyPair generateBase64RsaKeyPair() {
        return new Base64RsaKeyPair(this.keyPairGenerator.generateKeyPair());
    }

    private PrivateKey getPrivateKey(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(this.getDecodedBase64Text(base64PrivateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(keySpec);
    }

    private PublicKey getPublicKey(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(this.getDecodedBase64Text(base64PublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(keySpec);
    }

    private byte[] getDecodedBase64Text(String base64Text) {
        return Base64.getDecoder().decode(base64Text.getBytes());
    }

    private String getBase64EncodedText(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public String decrypt(String base64EncryptedText, String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        PrivateKey privateKey = this.getPrivateKey(base64PrivateKey);
        byte[] encryptedText = this.getDecodedBase64Text(base64EncryptedText);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(encryptedText));
    }

    public String encrypt(String message, String base64PublicKey) throws Exception{
        PublicKey publicKey = this.getPublicKey(base64PublicKey);

        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return getBase64EncodedText(encryptedBytes);
    }
}
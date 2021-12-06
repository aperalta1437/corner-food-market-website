package com.cornerfoodmarketwebsite;

import com.cornerfoodmarketwebsite.business.service.utils.Base64RsaKeyPair;
import com.cornerfoodmarketwebsite.business.service.utils.RsaUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class PasswordEncoder {
    public static void main(String[] args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Password1";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);

        Random rand = new Random();

        int n = rand.nextInt(899999) + 100000;

        System.out.println(n);

        RsaUtil rsaUtil = new RsaUtil();
        Base64RsaKeyPair base64RsaKeyPair = rsaUtil.generateBase64RsaKeyPair();

        String text = "Hello World!";
        System.out.println("Text: " + text);

        String encryptedBase64Text = rsaUtil.encrypt(text, base64RsaKeyPair.getBase64PublicKey());
        System.out.println("Encrypted Base64 Text: " + encryptedBase64Text);

        String decryptedText = rsaUtil.decrypt(encryptedBase64Text, base64RsaKeyPair.getBase64PrivateKey());
        System.out.println("Decrypted Text: " + decryptedText);

        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        String digest = bytesToHex(salt.digest());
        System.out.println(digest.length());
        System.out.println(digest);
        String encodedDigest = encoder.encode(digest);
        System.out.println(encodedDigest.length());
        System.out.println(encodedDigest);

    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}

package com.cornerfoodmarketwebsite;

import com.cornerfoodmarketwebsite.business.service.utils.Base64RsaKeyPair;
import com.cornerfoodmarketwebsite.business.service.utils.RsaUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

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

    }
}

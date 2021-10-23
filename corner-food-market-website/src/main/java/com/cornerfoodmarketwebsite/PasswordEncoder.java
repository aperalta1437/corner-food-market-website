package com.cornerfoodmarketwebsite;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Password1";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);

        Random rand = new Random();

        int n = rand.nextInt(899999) + 100000;

        System.out.println(n);

    }
}

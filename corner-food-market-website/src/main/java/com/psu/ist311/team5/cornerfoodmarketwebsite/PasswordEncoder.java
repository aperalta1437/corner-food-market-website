package com.psu.ist311.team5.cornerfoodmarketwebsite;

import com.psu.ist311.team5.cornerfoodmarketwebsite.controller.utils.LoginProcessIssue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Password1";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);

        Random rand = new Random();

        int n = rand.nextInt(8999999) + 1000000;

        System.out.println(n);

    }
}

package com.psu.ist311.team5.cornerfoodmarketwebsite;

import com.psu.ist311.team5.cornerfoodmarketwebsite.controller.utils.LoginProcessIssue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Password1";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}

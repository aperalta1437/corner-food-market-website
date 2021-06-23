package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public LoginController() {
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }
}
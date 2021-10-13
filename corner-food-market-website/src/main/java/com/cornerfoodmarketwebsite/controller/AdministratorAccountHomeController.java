package com.cornerfoodmarketwebsite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/account")
public class AdministratorAccountHomeController {

    @GetMapping
    public String getTest() {
        return "Hello from account World";
    }
}

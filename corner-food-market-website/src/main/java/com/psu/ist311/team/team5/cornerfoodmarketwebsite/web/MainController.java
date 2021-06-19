package com.psu.ist311.team.team5.cornerfoodmarketwebsite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    public MainController() {
    }

    @GetMapping
    public String getMainPage() {
        return "main";
    }
}

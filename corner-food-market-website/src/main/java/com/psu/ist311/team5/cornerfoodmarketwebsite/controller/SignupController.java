package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.model.Customer;
import com.psu.ist311.team5.cornerfoodmarketwebsite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("customer", new Customer());

        return "signup";
    }

    @PostMapping(value = "/process-signup")
    public String processSignup(Customer customer) {
        customerRepository.save(customer);

        return "signup-success";
    }
}

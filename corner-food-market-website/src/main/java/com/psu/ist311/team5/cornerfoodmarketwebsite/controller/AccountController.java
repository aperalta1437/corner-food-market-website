package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.Customer;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AccountController {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/account")
    public String getAccountMainPage(HttpServletResponse response) throws IOException {
        //Customer customer = customerRepository.findById(customerId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth == null) || !(auth.isAuthenticated()) || (auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/login");
        }

        return "account-home";
    }

//    @PostMapping(value = "/403")
//    public void getLoginPage(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/login");
//    }
}

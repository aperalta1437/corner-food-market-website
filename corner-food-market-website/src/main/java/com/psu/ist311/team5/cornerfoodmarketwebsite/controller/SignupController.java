package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form.SignupForm;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.SignupResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.SignupFormService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.DeliveryAddressRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SignupController {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final DeliveryAddressRepository deliveryAddressRepository;

    public SignupController(CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @GetMapping(value = "/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("signupForm", new SignupForm());

        return "signup";
    }

    @PostMapping(value = "/process-signup")
    public String processSignup(SignupForm signupForm, Model model) {
        SignupFormService signupFormService = new SignupFormService(this.customerRepository,
                this.deliveryAddressRepository);
        SignupResponse signupResponse = signupFormService.processNewSignup(signupForm);
        model.addAttribute("signupResponse", signupResponse);
        return "signup-response";
    }

    @ModelAttribute
    public void checkAuth(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/account");
        }
    }
}

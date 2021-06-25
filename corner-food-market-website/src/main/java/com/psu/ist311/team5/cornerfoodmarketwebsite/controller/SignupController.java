package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.CustomerPersonalInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.SignupResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.CustomerPersonalInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerAddressRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final CustomerAddressRepository customerAddressRepository;

    public SignupController(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    @GetMapping(value = "/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("customerPersonalInformation", new CustomerPersonalInformation());

        return "signup";
    }

    @PostMapping(value = "/process-signup")
    public String processSignup(CustomerPersonalInformation customerPersonalInformation, Model model) {
        CustomerPersonalInformationService customerPersonalInformationService = new CustomerPersonalInformationService(this.customerRepository,
                this.customerAddressRepository);
        SignupResponse signupResponse = customerPersonalInformationService.processNewSignup(customerPersonalInformation);
        model.addAttribute("signupResponse", signupResponse);
        return "signup-response";
    }
}

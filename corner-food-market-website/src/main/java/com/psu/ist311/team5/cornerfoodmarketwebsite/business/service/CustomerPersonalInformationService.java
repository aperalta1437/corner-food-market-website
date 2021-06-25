package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.CustomerPersonalInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.SignupResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.Customer;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerAddressRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerPersonalInformationService {
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    @Autowired
    public CustomerPersonalInformationService(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    public SignupResponse processNewSignup(CustomerPersonalInformation customerPersonalInformation) {
        if (this.customerRepository.existsByEmail(customerPersonalInformation.getEmail())) {
            return SignupResponse.EXISTING_EMAIL;
        } else {
            if (this.signupNewCustomer(customerPersonalInformation)) {
                return SignupResponse.SUCCESS;
            } else {
                return SignupResponse.SERVER_ERROR;
            }
        }
    }

    public boolean signupNewCustomer(CustomerPersonalInformation customerPersonalInformation) {
        Customer newCustomer = new Customer();
        newCustomer.setEmail(customerPersonalInformation.getEmail());
        newCustomer.setPassword(customerPersonalInformation.getPassword());
        newCustomer.setFirstName(customerPersonalInformation.getFirstName());
        newCustomer.setLastName(customerPersonalInformation.getLastName());
        newCustomer.setCellPhoneNumber(customerPersonalInformation.getCellPhoneNumber());
        try {
            this.customerRepository.save(newCustomer);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

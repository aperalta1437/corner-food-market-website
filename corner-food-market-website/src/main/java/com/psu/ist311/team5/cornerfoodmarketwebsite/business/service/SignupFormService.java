package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form.SignupForm;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.SignupResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.utils.CountryAlpha2Code;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.Customer;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.CustomerAddress;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerAddressRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupFormService {
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    @Autowired
    public SignupFormService(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    public SignupResponse processNewSignup(SignupForm signupForm) {
        if (this.customerRepository.existsByEmail(signupForm.getEmail())) {
            return SignupResponse.EXISTING_EMAIL;
        } else {
            if (this.signupNewCustomer(signupForm)) {
                return SignupResponse.SUCCESS;
            } else {
                return SignupResponse.SERVER_ERROR;
            }
        }
    }

    public boolean signupNewCustomer(SignupForm signupForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(signupForm.getPassword());
        Customer newCustomer = new Customer();

        newCustomer.setEmail(signupForm.getEmail());
        newCustomer.setPassword(encodedPassword);
        newCustomer.setFirstName(signupForm.getFirstName());
        newCustomer.setMiddleName(signupForm.getMiddleName());
        newCustomer.setLastName(signupForm.getLastName());
        newCustomer.setCellPhoneNumber(signupForm.getCellPhoneNumber());

        boolean isNewCustomerSaved;
        try {
            this.customerRepository.save(newCustomer);
            isNewCustomerSaved = true;
        } catch (Exception ex) {
            // TODO - Log the exception message.
            isNewCustomerSaved = false;
        }

        if (signupForm.getIsAddressProvided()) {
            CustomerAddress newCustomerAddress = new CustomerAddress();

            newCustomerAddress.setCustomerId(this.customerRepository.getIdByEmail(newCustomer.getEmail()));

            newCustomerAddress.setAddressLine1(signupForm.getAddressLine1());
            newCustomerAddress.setAddressLine2(signupForm.getAddressLine2());
            newCustomerAddress.setCity(signupForm.getCity());

            String countryAlpha2Code = signupForm.getCountryAlpha2Code();

            if (CountryAlpha2Code.valueOf(countryAlpha2Code) == CountryAlpha2Code.US) {
                newCustomerAddress.setStateCode(signupForm.getStateCode());
            }
            newCustomerAddress.setPostalCode(signupForm.getPostalCode());
            newCustomerAddress.setCountryAlpha2Code(countryAlpha2Code);

            try {
                this.customerAddressRepository.save(newCustomerAddress);
            } catch (Exception ex) {
                // TODO - Log the exception message.
            }
        }

        System.out.println(signupForm.getAddressLine1());
        System.out.println(signupForm.getAddressLine2());
        System.out.println(signupForm.getCity());
        System.out.println(signupForm.getPostalCode());
        System.out.println(signupForm.getStateCode());
        System.out.println(signupForm.getCountryAlpha2Code());
        System.out.println(signupForm.getIsAddressProvided());

        return isNewCustomerSaved;
    }
}

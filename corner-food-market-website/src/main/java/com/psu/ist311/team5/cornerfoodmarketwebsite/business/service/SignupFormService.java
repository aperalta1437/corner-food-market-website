package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form.SignupForm;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.SignupResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.utils.CountryAlpha2Code;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.Customer;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.DeliveryAddress;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.DeliveryAddressRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupFormService {
    private final CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    public SignupFormService(CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
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
            this.addDeliveryAddress(signupForm, this.customerRepository.getIdByEmail(newCustomer.getEmail()));
        }
        return isNewCustomerSaved;
    }

    public void addDeliveryAddress(SignupForm signupForm, short newCustomerId) {
        DeliveryAddress newDeliveryAddress = new DeliveryAddress();

        newDeliveryAddress.setCustomerId(newCustomerId);

        newDeliveryAddress.setAddressLine1(signupForm.getAddressLine1());
        newDeliveryAddress.setAddressLine2(signupForm.getAddressLine2());
        newDeliveryAddress.setCity(signupForm.getCity());

        String countryAlpha2Code = signupForm.getCountryAlpha2Code();

        if (CountryAlpha2Code.valueOf(countryAlpha2Code) == CountryAlpha2Code.US) {
            newDeliveryAddress.setStateCode(signupForm.getStateCode());
        }
        newDeliveryAddress.setPostalCode(signupForm.getPostalCode());
        newDeliveryAddress.setCountryAlpha2Code(countryAlpha2Code);

        try {
            this.deliveryAddressRepository.save(newDeliveryAddress);
        } catch (Exception ex) {
            // TODO - Log the exception message.
        }
    }
}

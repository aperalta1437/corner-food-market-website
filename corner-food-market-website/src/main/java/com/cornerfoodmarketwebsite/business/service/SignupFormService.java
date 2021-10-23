package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.request.form.SignupForm;
import com.cornerfoodmarketwebsite.business.dto.response.SignupResponseEnum;
import com.cornerfoodmarketwebsite.business.service.utils.CountryAlpha2CodeEnum;
import com.cornerfoodmarketwebsite.data.single_table.entity.Customer;
import com.cornerfoodmarketwebsite.data.single_table.repository.CustomerRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.DeliveryAddressRepository;
import com.cornerfoodmarketwebsite.data.single_table.entity.DeliveryAddress;
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

    public SignupResponseEnum processNewSignup(SignupForm signupForm) {
        if (this.customerRepository.existsByEmail(signupForm.getEmail())) {
            return SignupResponseEnum.EXISTING_EMAIL;
        } else {
            if (this.signupNewCustomer(signupForm)) {
                return SignupResponseEnum.SUCCESS;
            } else {
                return SignupResponseEnum.SERVER_ERROR;
            }
        }
    }

    public boolean signupNewCustomer(SignupForm signupForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();        // TODO replaced this encoder with spring security bean for customer.
        String encodedPassword = encoder.encode(signupForm.getPassword());
        Customer newCustomer = new Customer(signupForm.getEmail(), encodedPassword, signupForm.getFirstName(),
                signupForm.getMiddleName(), signupForm.getLastName(), signupForm.getCellPhoneNumber(), false,
                (short) 0);

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

        if (CountryAlpha2CodeEnum.valueOf(countryAlpha2Code) == CountryAlpha2CodeEnum.US) {
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

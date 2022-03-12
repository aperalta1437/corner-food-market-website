package com.cornerfoodmarketwebsite.business.dto.request.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String cellPhoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateCode;
    private String postalCode;
    private String countryAlpha2Code;
    private boolean isAddressProvided;
}
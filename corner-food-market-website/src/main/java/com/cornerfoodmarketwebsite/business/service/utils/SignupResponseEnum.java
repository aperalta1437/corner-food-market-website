package com.cornerfoodmarketwebsite.business.service.utils;

public enum SignupResponseEnum {
    EMAIL_VERIFICATION_NEEDED("In order to finalize the signup process, you must verify the provided e-mail address by following the instructions on the e-mail we sent to the provided e-mail address."),
    SUCCESS("You have signed up successfully!"),
    EXISTING_EMAIL("The email you provided is already associated with an existing account"),
    UNPROCESSED_DELIVERY_ADDRESS("We received your signup information; however, we were unable to process the delivery address. Please try adding the delivery address again later, and if the issue persist, please report it to the store manager. In order to finalize the signup process, you must verify the provided e-mail address by following the instructions on the e-mail we sent to the provided e-mail address."),
    SERVER_ERROR("An error occurred when we tried to sign you up. Please try again later, and if the issue persist, please report it to the store manager.");

    private final String signupMessage;

    SignupResponseEnum(String signupMessage) {
        this.signupMessage = signupMessage;
    }

    public String getSignupMessage() {
        return this.signupMessage;
    }
}
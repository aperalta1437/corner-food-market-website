package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response;

public enum SignupResponse {
    EXISTING_EMAIL("The email you provided is already associated with an existing account"),
    SUCCESS("You have signed up successfully!"),
    SERVER_ERROR("An error occurred while processing your request. Please try again later.");

    private final String signupMessage;

    SignupResponse(String signupMessage) {
        this.signupMessage = signupMessage;
    }

    public String getSignupMessage() {
        return this.signupMessage;
    }
}

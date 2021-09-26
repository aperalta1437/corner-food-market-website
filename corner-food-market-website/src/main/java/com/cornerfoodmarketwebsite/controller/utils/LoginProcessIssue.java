package com.cornerfoodmarketwebsite.controller.utils;

public enum LoginProcessIssue {
    NONE("You have logged in successfully!"),
    FAILED_LOGIN("The email and/or password you provided don't match any existing account"),
    EXPIRED_SESSION("Your previous session expired"),
    DISABLED_ACCOUNT("Your account has been disabled. Please, contact the store if you wish to able it.");

    private final String loginIssueMessage;

    LoginProcessIssue(String loginIssueMessage) {
        this.loginIssueMessage = loginIssueMessage;
    }

    public String getLoginIssueMessage() {
        return this.loginIssueMessage;
    }
}

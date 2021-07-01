package com.psu.ist311.team5.cornerfoodmarketwebsite.controller.utils;

public enum LoginProcessIssue {
    NONE("You have logged in successfully!"),
    FAILED_LOGIN("The email and/or password you provided don't match any existing account"),
    EXPIRED_SESSION("Your previous session expired");

    private final String loginIssueMessage;

    LoginProcessIssue(String loginIssueMessage) {
        this.loginIssueMessage = loginIssueMessage;
    }

    public String getLoginIssueMessage() {
        return this.loginIssueMessage;
    }
}

package com.cornerfoodmarketwebsite.business.dto.request.form;

public class AdministratorLoginForm {
    private String username;
    private String password;

    public AdministratorLoginForm() {
    }

    public AdministratorLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

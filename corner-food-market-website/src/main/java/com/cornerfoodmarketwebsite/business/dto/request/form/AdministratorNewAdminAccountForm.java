package com.cornerfoodmarketwebsite.business.dto.request.form;

public class AdministratorNewAdminAccountForm {
    private final String email;
    private final String cellPhoneNumber;

    public AdministratorNewAdminAccountForm(String email, String cellPhoneNumber) {
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}

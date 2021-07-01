package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryAlpha2Code() {
        return countryAlpha2Code;
    }

    public void setCountryAlpha2Code(String countryAlpha2Code) {
        this.countryAlpha2Code = countryAlpha2Code;
    }

    public boolean getIsAddressProvided() {
        return isAddressProvided;
    }

    public void setIsAddressProvided(boolean isAddressProvided) {
        this.isAddressProvided = isAddressProvided;
    }
}

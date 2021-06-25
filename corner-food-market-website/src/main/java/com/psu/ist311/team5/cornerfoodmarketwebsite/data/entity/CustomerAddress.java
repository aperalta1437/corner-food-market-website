package com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;
    @Column(name = "CITY")
    private String city;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}

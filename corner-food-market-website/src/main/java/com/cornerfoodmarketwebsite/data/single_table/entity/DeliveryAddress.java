package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY_ADDRESS")
@Getter
@Setter
public class DeliveryAddress {
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
    @Column(name = "STATE_CODE")
    private String stateCode;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "COUNTRY_ALPHA2_CODE")
    private String countryAlpha2Code;
    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;
}

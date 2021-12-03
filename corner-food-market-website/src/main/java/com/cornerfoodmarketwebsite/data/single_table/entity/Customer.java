package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "CELL_PHONE_NUMBER")
    private String cellPhoneNumber;
    @Column(name = "IS_DISABLED")
    private boolean isDisabled;
    @Column(name = "TOTAL_CART_ITEMS")
    private short totalCartItems;

    public Customer() {
    }

    public Customer(String email, String password, String firstName, String middleName, String lastName, String cellPhoneNumber, boolean isDisabled, short totalCartItems) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.isDisabled = isDisabled;
        this.totalCartItems = totalCartItems;
    }

    public short getId() {
        return id;
    }

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public short getTotalCartItems() {
        return totalCartItems;
    }

    public void setTotalCartItems(short totalCartItems) {
        this.totalCartItems = totalCartItems;
    }

    public void addNumberOfItemsToCart(short numberOfItemsToAdd) {
        this.totalCartItems += numberOfItemsToAdd;
    }
}

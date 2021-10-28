package com.cornerfoodmarketwebsite.data.single_table.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ADMINISTRATOR")
public class Administrator {
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
    @Column(name = "IS_TFA_ENABLED")
    private boolean isTfaEnabled;
    @Column(name = "TFA_CODE")
    private String tfaCode;
    @Column(name = "TFA_EXPIRATION_TIME")
    private Timestamp tfaExpirationTime;
    @Column(name = "TFA_CHOSEN_TYPE")
    private TfaTypeEnum tfaChosenType;
    @Column(name = "RSA_PRIVATE_KEY")
    private String rsaPrivateKey;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;

    public Administrator() {
    }

    public Administrator(String email, String password, String firstName, String middleName, String lastName,
                         String cellPhoneNumber, boolean isDisabled, boolean isTfaEnabled, String tfaCode,
                         Timestamp tfaExpirationTime, TfaTypeEnum tfaChosenType) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.isDisabled = isDisabled;
        this.isTfaEnabled = isTfaEnabled;
        this.tfaCode = tfaCode;
        this.tfaExpirationTime = tfaExpirationTime;
        this.tfaChosenType = tfaChosenType;
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

    public boolean isTfaEnabled() {
        return isTfaEnabled;
    }

    public void setTfaEnabled(boolean tfaEnabled) {
        isTfaEnabled = tfaEnabled;
    }

    public String getTfaCode() {
        return tfaCode;
    }

    public void setTfaCode(String tfaCode) {
        this.tfaCode = tfaCode;
    }

    public Timestamp getTfaExpirationTime() {
        return tfaExpirationTime;
    }

    public void setTfaExpirationTime(Timestamp tfaExpirationTime) {
        this.tfaExpirationTime = tfaExpirationTime;
    }

    public TfaTypeEnum getTfaChosenType() {
        return tfaChosenType;
    }

    public void setTfaChosenType(TfaTypeEnum tfaChosenType) {
        this.tfaChosenType = tfaChosenType;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }
}

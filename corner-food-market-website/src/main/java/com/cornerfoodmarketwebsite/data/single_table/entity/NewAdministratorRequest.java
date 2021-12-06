package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "NEW_ADMINISTRATOR_REQUEST")
public class NewAdministratorRequest {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CELL_PHONE_NUMBER")
    private String cellPhoneNumber;
    @Column(name = "UUID")
    private String uuid;
    @Column(name = "EXPIRATION_DATETIME")
    private Timestamp expirationDatetime;
    @Column(name = "IS_CANCELLED")
    private boolean isCancelled;
    @Column(name = "IS_USED")
    private boolean isUsed;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @Column(name = "CANCELLED_AT")
    private Timestamp cancelledAt;

    public NewAdministratorRequest() {
    }

    public NewAdministratorRequest(String email, String cellPhoneNumber, String uuid, Timestamp expirationDatetime, boolean isCancelled, boolean isUsed) {
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.uuid = uuid;
        this.expirationDatetime = expirationDatetime;
        this.isCancelled = isCancelled;
        this.isUsed = isUsed;
    }

    public short getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public Timestamp getExpirationDatetime() {
        return expirationDatetime;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getCancelledAt() {
        return cancelledAt;
    }
}

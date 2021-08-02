package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PAYMENT_DETAILS")
public class PaymentDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "PROVIDER_ID")
    private short providerId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATED_AT")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public short getProviderId() {
        return providerId;
    }

    public void setProviderId(short providerId) {
        this.providerId = providerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}

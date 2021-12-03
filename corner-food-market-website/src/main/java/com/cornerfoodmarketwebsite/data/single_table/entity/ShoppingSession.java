package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHOPPING_SESSION")
public class ShoppingSession {
    @Id
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Column(name = "SUBTOTAL")
    private double subtotal;

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}

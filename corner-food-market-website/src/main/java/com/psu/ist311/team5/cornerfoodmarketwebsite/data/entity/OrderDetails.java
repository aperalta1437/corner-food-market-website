package com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Column(name = "FIRST_PAYMENT_ID")
    private Integer firstPaymentId;
    @Column(name = "SECOND_PAYMENT_ID")
    private Integer secondPaymentId;
    @Column(name = "THIRD_PAYMENT_ID")
    private Integer thirdPaymentId;
    @Column(name = "SUBTOTAL")
    private Integer subtotal;
    @Column(name = "TOTAL")
    private Integer total;
    @Column(name = "TAXES")
    private Integer taxes;
    @Column(name = "CREATED_AT")
    private Integer createdAt;

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

    public Integer getFirstPaymentId() {
        return firstPaymentId;
    }

    public void setFirstPaymentId(Integer firstPaymentId) {
        this.firstPaymentId = firstPaymentId;
    }

    public Integer getSecondPaymentId() {
        return secondPaymentId;
    }

    public void setSecondPaymentId(Integer secondPaymentId) {
        this.secondPaymentId = secondPaymentId;
    }

    public Integer getThirdPaymentId() {
        return thirdPaymentId;
    }

    public void setThirdPaymentId(Integer thirdPaymentId) {
        this.thirdPaymentId = thirdPaymentId;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTaxes() {
        return taxes;
    }

    public void setTaxes(Integer taxes) {
        this.taxes = taxes;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }
}

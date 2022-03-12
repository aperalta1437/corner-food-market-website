package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PAYMENT_DETAILS")
@Getter
@Setter
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
}

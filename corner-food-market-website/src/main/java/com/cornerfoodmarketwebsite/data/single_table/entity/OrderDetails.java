package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ORDER_DETAILS")
@Getter
@Setter
public class OrderDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ORDER_NUMBER")
    private int orderNumber;
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
    private Date createdAt;
}

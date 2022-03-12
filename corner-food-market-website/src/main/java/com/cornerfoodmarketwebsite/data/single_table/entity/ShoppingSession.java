package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SHOPPING_SESSION")
@Getter
@Setter
public class ShoppingSession {
    @Id
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Column(name = "SUBTOTAL")
    private double subtotal;
}

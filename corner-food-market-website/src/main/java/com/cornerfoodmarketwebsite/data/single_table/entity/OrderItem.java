package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ORDER_ID")
    private int orderId;
    @Column(name = "ITEM_ID")
    private short itemId;
    @Column(name = "QUANTITY")
    private short quantity;
}

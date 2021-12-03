package com.cornerfoodmarketwebsite.data.single_table.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_INVENTORY")
public class ItemInventory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "QUANTITY")
    private short quantity;

    public ItemInventory(short quantity) {
        this.quantity = quantity;
    }

    public short getId() {
        return id;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}

package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@IdClass(value = CartItemId.class)
public class CartItem {
    @Id
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Id
    @Column(name = "ITEM_ID")
    private short itemId;
    @Column(name = "QUANTITY")
    private short providerId;

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    public short getItemId() {
        return itemId;
    }

    public void setItemId(short itemId) {
        this.itemId = itemId;
    }

    public short getProviderId() {
        return providerId;
    }

    public void setProviderId(short providerId) {
        this.providerId = providerId;
    }
}

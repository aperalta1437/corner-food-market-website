package com.cornerfoodmarketwebsite.data.single_table.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@IdClass(value = CartItemId.class)
//@FilterDef(name = "filterByCustomerId", parameters = @ParamDef(name = "customerId", type = "short"))
//@Filter(name = "filterByCustomerId", condition = "customerId=:customerId")
public class CartItem {
    @Id
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Id
    @Column(name = "ITEM_ID")
    private short itemId;
    @Column(name = "IN_CART_QUANTITY")
    private short inCartQuantity;

    public CartItem() {
    }

    public CartItem(short customerId, short itemId, short inCartQuantity) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.inCartQuantity = inCartQuantity;
    }

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

    public short getInCartQuantity() {
        return inCartQuantity;
    }

    public void setInCartQuantity(short inCartQuantity) {
        this.inCartQuantity = inCartQuantity;
    }
}

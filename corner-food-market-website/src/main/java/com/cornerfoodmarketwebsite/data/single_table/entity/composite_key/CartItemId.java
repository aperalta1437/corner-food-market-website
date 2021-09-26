package com.cornerfoodmarketwebsite.data.single_table.entity.composite_key;

import java.io.Serializable;

public class CartItemId implements Serializable {
    private short customerId;
    private short itemId;

    public CartItemId() {
    }

    public CartItemId(short customerId, short itemId) {
        this.customerId = customerId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CartItemId)) {
            return false;
        }

        CartItemId cartItemId = (CartItemId) o;

        return Short.compare(this.customerId, cartItemId.customerId) == 0 &&
                Short.compare(this.itemId, cartItemId.itemId) == 0;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(Short.toString(this.customerId) + Short.toString(this.itemId));
    }
}
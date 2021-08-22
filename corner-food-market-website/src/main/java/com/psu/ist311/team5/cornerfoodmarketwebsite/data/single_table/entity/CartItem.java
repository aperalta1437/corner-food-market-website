package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.CustomUserDetails;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.springframework.security.core.context.SecurityContextHolder;

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
    private short quantity;

    public CartItem() {
    }

    public CartItem(short customerId, short itemId, short quantity) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.quantity = quantity;
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

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}

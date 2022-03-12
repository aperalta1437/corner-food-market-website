package com.cornerfoodmarketwebsite.data.single_table.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.composite_key.CartItemId;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
@IdClass(value = CartItemId.class)
//@FilterDef(name = "filterByCustomerId", parameters = @ParamDef(name = "customerId", type = "short"))
//@Filter(name = "filterByCustomerId", condition = "customerId=:customerId")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CartItem {
    @Id
    @NonNull
    @Column(name = "CUSTOMER_ID")
    private short customerId;
    @Id
    @NonNull
    @Column(name = "ITEM_ID")
    private short itemId;
    @NonNull
    @Column(name = "IN_CART_QUANTITY")
    private short inCartQuantity;
}

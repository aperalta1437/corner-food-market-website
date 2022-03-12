package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NonNull
    @Column(name = "EMAIL")
    private String email;
    @NonNull
    @Column(name = "PASSWORD")
    private String password;
    @NonNull
    @Column(name = "FIRST_NAME")
    private String firstName;
    @NonNull
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @NonNull
    @Column(name = "LAST_NAME")
    private String lastName;
    @NonNull
    @Column(name = "CELL_PHONE_NUMBER")
    private String cellPhoneNumber;
    @NonNull
    @Column(name = "IS_VERIFIED")
    private boolean isVerified;
    @NonNull
    @Column(name = "VERIFICATION_UUID")
    private String verificationUuid;
    @NonNull
    @Column(name = "IS_DISABLED")
    private boolean isDisabled;
    @NonNull
    @Column(name = "TOTAL_CART_ITEMS")
    private short totalCartItems;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    public void addNumberOfItemsToCart(short numberOfItemsToAdd) {
        this.totalCartItems += numberOfItemsToAdd;
    }
}

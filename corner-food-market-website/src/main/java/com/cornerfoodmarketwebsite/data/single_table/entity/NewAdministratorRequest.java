package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "NEW_ADMINISTRATOR_REQUEST")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class NewAdministratorRequest {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NonNull
    @Column(name = "EMAIL")
    private String email;
    @NonNull
    @Column(name = "CELL_PHONE_NUMBER")
    private String cellPhoneNumber;
    @NonNull
    @Column(name = "UUID")
    private String uuid;
    @NonNull
    @Column(name = "EXPIRATION_DATETIME")
    private Timestamp expirationDatetime;
    @NonNull
    @Column(name = "IS_CANCELLED")
    private boolean isCancelled;
    @NonNull
    @Column(name = "IS_USED")
    private boolean isUsed;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @Column(name = "CANCELLED_AT")
    private Timestamp cancelledAt;
}

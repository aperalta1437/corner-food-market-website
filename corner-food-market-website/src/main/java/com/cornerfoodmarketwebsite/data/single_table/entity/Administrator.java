package com.cornerfoodmarketwebsite.data.single_table.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ADMINISTRATOR")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Administrator {
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
    @Column(name = "IS_DISABLED")
    private boolean isDisabled;
    @NonNull
    @Column(name = "IS_TFA_ENABLED")
    private boolean isTfaEnabled;
    @Column(name = "TFA_CODE")
    private String tfaCode;
    @Column(name = "TFA_EXPIRATION_TIME")
    private Long tfaExpirationTime;
    @NonNull
    @Column(name = "TFA_CHOSEN_TYPE")
    private TfaTypeEnum tfaChosenType;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;
}

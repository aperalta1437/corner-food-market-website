package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "GENERAL_REVIEW")
@Where(clause = "IS_DELETED = false")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class GeneralReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    @NonNull
    @Column(name = "IS_ANONYMOUS_TO_CUSTOMERS")
    private boolean isAnonymousToCustomers;
    @NonNull
    @Column(name = "IS_ANONYMOUS_TO_EVERYONE")
    private boolean isAnonymousToEveryone;
    @NonNull
    @Column(name = "IS_REGISTERED_USER")
    private boolean isRegisteredUser;
    @NonNull
    @Column(name = "IS_ACTIVE_SHOPPER")
    private boolean isActiveShopper;
    @NonNull
    @Column(name = "SUBJECT_LINE")
    private String subjectLine;
    @NonNull
    @Column(name = "COMMENT")
    private String comment;
    @NonNull
    @Column(name = "STAR_RATING")
    private short starRating;
    @NonNull
    @Column(name = "IS_DELETED")
    private boolean isDeleted;
    @Column(name = "CREATED_AT")
    private Date createdAt;
}

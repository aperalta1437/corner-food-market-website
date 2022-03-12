package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ITEM_REVIEW")
@Where(clause = "IS_DELETED = false")
@Getter
@Setter
public class ItemReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "ITEM_ID")
    private short itemId;
    @Column(name = "IS_ANONYMOUS_TO_CUSTOMERS")
    private boolean isAnonymousToCustomers;
    @Column(name = "IS_ANONYMOUS_TO_EVERYONE")
    private boolean isAnonymousToEveryone;
    @Column(name = "IS_REGISTERED_USER")
    private boolean isRegisteredUser;
    @Column(name = "IS_ACTIVE_SHOPPER")
    private boolean isActiveShopper;
    @Column(name = "SUBJECT_LINE")
    private String subjectLine;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "STAR_RATING")
    private float starRating;
    @Column(name = "IS_DELETED")
    private boolean isDeleted;
    @Column(name = "CREATED_AT")
    private Date createdAt;
}

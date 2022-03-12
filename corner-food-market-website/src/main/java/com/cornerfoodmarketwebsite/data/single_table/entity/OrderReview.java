package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ORDER_REVIEW")
@Where(clause = "IS_DELETED = false")
@Getter
@Setter
public class OrderReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "ORDER_ID")
    private short orderId;
    @Column(name = "IS_ANONYMOUS_TO_CUSTOMERS")
    private boolean isAnonymousToCustomers;
    @Column(name = "IS_ANONYMOUS_TO_EVERYONE")
    private boolean isAnonymousToEveryone;
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

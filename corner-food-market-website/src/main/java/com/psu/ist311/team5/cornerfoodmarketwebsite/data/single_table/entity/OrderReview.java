package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ORDER_REVIEW")
@Where(clause = "IS_DELETED = false")
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
    @Column(name = "REVIEW_TEXT")
    private String reviewText;
    @Column(name = "STAR_RATING")
    private float starRating;
    @Column(name = "IS_DELETED")
    private boolean isDeleted;
    @Column(name = "CREATED_AT")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Short getItemId() {
        return orderId;
    }

    public void setItemId(Short orderId) {
        this.orderId = orderId;
    }

    public boolean getIsAnonymousToCustomers() {
        return isAnonymousToCustomers;
    }

    public void setIsAnonymousToCustomers(boolean isAnonymousToCustomers) {
        this.isAnonymousToCustomers = isAnonymousToCustomers;
    }

    public boolean getIsAnonymousToEveryone() {
        return isAnonymousToEveryone;
    }

    public void setIsAnonymousToEveryone(boolean isAnonymousToEveryone) {
        this.isAnonymousToEveryone = isAnonymousToEveryone;
    }

    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public float getStarRating() {
        return starRating;
    }

    public void setStarRating(float startRating) {
        this.starRating = startRating;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}

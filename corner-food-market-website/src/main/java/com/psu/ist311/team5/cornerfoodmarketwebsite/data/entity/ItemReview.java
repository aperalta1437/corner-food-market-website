package com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ITEM_REVIEW")
public class ItemReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CUSTOMER_ID")
    private Short customerId;
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

    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Short getItemId() {
        return itemId;
    }

    public void setItemId(Short itemId) {
        this.itemId = itemId;
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

    public boolean getIsRegisteredUser() {
        return isRegisteredUser;
    }

    public void setIsRegisteredUser(boolean isRegisteredUser) {
        this.isRegisteredUser = isRegisteredUser;
    }

    public boolean getIsActiveShopper() {
        return isActiveShopper;
    }

    public void setIsActiveShopper(boolean isActiveShopper) {
        this.isActiveShopper = isActiveShopper;
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

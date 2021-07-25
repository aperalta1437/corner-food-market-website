package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain;

import javax.persistence.Column;
import java.sql.Date;

public class ReviewInformation {

    private String customerFullName;
    private String subjectLine;
    private String reviewText;
    private float starRating;
    private Date createdAt;

    public ReviewInformation(String customerFullName, String subjectLine, String reviewText, float starRating, Date createdAt) {
        this.customerFullName = customerFullName;
        this.subjectLine = subjectLine;
        this.reviewText = reviewText;
        this.starRating = starRating;
        this.createdAt = createdAt;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
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

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

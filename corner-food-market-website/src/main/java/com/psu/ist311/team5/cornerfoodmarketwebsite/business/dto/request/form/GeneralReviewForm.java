package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form;

public class GeneralReviewForm {
    private String subjectLine;
    private String reviewText;
    private float starRating;

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
}

package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form;

public class AnonymousGeneralReviewForm {
    private String subjectLine;
    private String comment;
    private short starRating;

    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public short getStarRating() {
        return starRating;
    }

    public void setStarRating(short starRating) {
        this.starRating = starRating;
    }
}

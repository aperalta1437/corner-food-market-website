package com.cornerfoodmarketwebsite.business.dto.response.domain;

import java.sql.Date;


public class AccountReviewInformation extends ReviewInformation{

    public AccountReviewInformation(String customerFullName, String subjectLine, String reviewText, float starRating, Date createdAt) {
        super(customerFullName, subjectLine, reviewText, starRating, createdAt);
    }
}

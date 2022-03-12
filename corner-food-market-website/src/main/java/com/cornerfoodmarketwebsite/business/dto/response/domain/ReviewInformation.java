package com.cornerfoodmarketwebsite.business.dto.response.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class ReviewInformation {
    @NonNull
    private String customerFullName;
    @NonNull
    private String subjectLine;
    @NonNull
    private String reviewText;
    @NonNull
    private float starRating;
    @NonNull
    private Date createdAt;
}

package com.cornerfoodmarketwebsite.business.dto.request.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnonymousGeneralReviewForm {
    private String subjectLine;
    private String comment;
    private short starRating;
}

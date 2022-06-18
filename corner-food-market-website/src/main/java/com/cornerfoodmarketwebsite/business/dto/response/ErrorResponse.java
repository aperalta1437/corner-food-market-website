package com.cornerfoodmarketwebsite.business.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {
    private final int statusCode;
    private final String message;
    private final String error;
}

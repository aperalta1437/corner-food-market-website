package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class InvalidAccessTokenRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private static final String message = "Expired or invalid access token";
    private static final String error = "INVALID_ACCESS_TOKEN";

    public InvalidAccessTokenRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

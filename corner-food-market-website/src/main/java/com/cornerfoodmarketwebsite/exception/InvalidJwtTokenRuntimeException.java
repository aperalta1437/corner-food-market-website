package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class InvalidJwtTokenRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private static final String message = "Expired or invalid JWT token";
    private static final String error = "INVALID_JWT_TOKEN";

    public InvalidJwtTokenRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

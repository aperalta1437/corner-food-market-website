package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class FailedFirstFactorAuthenticationRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private static final String message = "Invalid email and/or password.";
    private static final String error = "FAILED_FIRST_FACTOR_AUTHENTICATION";

    public FailedFirstFactorAuthenticationRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

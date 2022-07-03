package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ExpiredTfaCodeRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.GONE;   // Using 410 here to differentiate from expired TFA JWT.
    private static final String message = "The 2-factor authentication code has expired";
    private static final String error = "EXPIRED_TFA_CODE";

    public ExpiredTfaCodeRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

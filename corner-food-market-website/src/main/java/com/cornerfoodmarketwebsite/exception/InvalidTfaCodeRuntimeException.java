package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class InvalidTfaCodeRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    private static final String message = "Incorrect security code was given";
    private static final String error = "INVALID_TFA_CODE";

    public InvalidTfaCodeRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

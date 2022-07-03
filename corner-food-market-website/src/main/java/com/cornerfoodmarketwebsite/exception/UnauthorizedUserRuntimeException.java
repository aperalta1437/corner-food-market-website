package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UnauthorizedUserRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private static final String message = "User is not authorized";
    private static final String error = "UNAUTHORIZED_USER";

    public UnauthorizedUserRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

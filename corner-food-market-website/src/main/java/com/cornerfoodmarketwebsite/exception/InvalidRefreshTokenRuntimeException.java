package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private static final String message = "Expired or invalid refresh token";
    private static final String error = "INVALID_REFRESH_TOKEN";

    public InvalidRefreshTokenRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

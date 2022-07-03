package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class NonExpiredJwtAccessTokenRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private static final String message = "Non-expired JWT access token";
    private static final String error = "NON_EXPIRED_JWT_ACCESS_TOKEN";

    public NonExpiredJwtAccessTokenRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

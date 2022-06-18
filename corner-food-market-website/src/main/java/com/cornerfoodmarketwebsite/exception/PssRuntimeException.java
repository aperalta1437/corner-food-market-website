package com.cornerfoodmarketwebsite.exception;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PssRuntimeException extends RuntimeException {
    private final ErrorResponse errorResponse;
    private final HttpStatus httpStatus;

    public PssRuntimeException(ErrorResponse errorResponse, HttpStatus httpStatus) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
        this.httpStatus = httpStatus;
    }
}

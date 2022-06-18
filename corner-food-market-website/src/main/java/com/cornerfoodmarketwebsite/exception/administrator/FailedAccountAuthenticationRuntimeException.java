package com.cornerfoodmarketwebsite.exception.administrator;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import com.cornerfoodmarketwebsite.exception.PssRuntimeException;
import org.springframework.http.HttpStatus;

public class FailedAccountAuthenticationRuntimeException extends PssRuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
    private static final String message = "We were unable to authenticate your account. Please try again later. If the issue persist, please contact your system administrator.";
    private static final String error = "FAILED_ACCOUNT_AUTHENTICATION";

    public FailedAccountAuthenticationRuntimeException() {
        super(new ErrorResponse(httpStatus.value(), message, error), httpStatus);
    }
}

package com.cornerfoodmarketwebsite.exception.handler;

import com.cornerfoodmarketwebsite.business.dto.response.ErrorResponse;
import com.cornerfoodmarketwebsite.business.service.ExceptionLogService;
import com.cornerfoodmarketwebsite.exception.PssRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final ExceptionLogService exceptionLogService;

    @ExceptionHandler(value = {PssRuntimeException.class})
    public ResponseEntity<ErrorResponse> handleExpectedError(PssRuntimeException pssRuntimeException) {
        return new ResponseEntity<>(pssRuntimeException.getErrorResponse(), pssRuntimeException.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleUnexpectedError(Exception exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        exceptionLogService.logException(exception);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "UNEXPECTED_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

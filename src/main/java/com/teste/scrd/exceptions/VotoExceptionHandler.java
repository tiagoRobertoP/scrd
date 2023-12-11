package com.teste.scrd.exceptions;

import com.teste.scrd.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class VotoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {VotoException.class})
    protected ResponseEntity<Object> handleOrderErrors(VotoException votoException, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                votoException.getErrors().getMessage(),
                votoException.getErrors().getHttpStatus().value()
        );
        return handleExceptionInternal(
                votoException,
                errorResponse,
                new HttpHeaders(),
                votoException.getErrors().getHttpStatus(),
                webRequest
        );
    }
}

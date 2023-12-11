package com.teste.scrd.exceptions;

import com.teste.scrd.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AssembleiaExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AssembleiaException.class})
    protected ResponseEntity<Object> handleOrderErrors(AssembleiaException assembleiaException, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                assembleiaException.getErrors().getMessage(),
                assembleiaException.getErrors().getHttpStatus().value()
        );
        return handleExceptionInternal(
                assembleiaException,
                errorResponse,
                new HttpHeaders(),
                assembleiaException.getErrors().getHttpStatus(),
                webRequest
        );
    }
}

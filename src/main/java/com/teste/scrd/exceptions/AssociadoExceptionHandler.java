package com.teste.scrd.exceptions;

import com.teste.scrd.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AssociadoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AssociadoException.class})
    protected ResponseEntity<Object> handleOrderErrors(AssociadoException associadoException , WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                associadoException.getErrors().getMessage(),
                associadoException.getErrors().getHttpStatus().value()
        );
        return handleExceptionInternal(
                associadoException,
                errorResponse,
                new HttpHeaders(),
                associadoException.getErrors().getHttpStatus(),
                webRequest
        );
    }
}

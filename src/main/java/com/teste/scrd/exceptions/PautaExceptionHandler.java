package com.teste.scrd.exceptions;

import com.teste.scrd.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PautaExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PautaException.class})
    protected ResponseEntity<Object> handleOrderErrors(PautaException pautaException , WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                pautaException.getErrors().getMessage(),
                pautaException.getErrors().getHttpStatus().value()
        );
        return handleExceptionInternal(
                pautaException,
                errorResponse,
                new HttpHeaders(),
                pautaException.getErrors().getHttpStatus(),
                webRequest
        );
    }
}

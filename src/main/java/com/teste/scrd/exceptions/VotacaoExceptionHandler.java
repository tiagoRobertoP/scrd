package com.teste.scrd.exceptions;

import com.teste.scrd.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class VotacaoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {VotacaoException.class})
    protected ResponseEntity<Object> handleOrderErrors(VotacaoException votacaoException, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                votacaoException.getErrors().getMessage(),
                votacaoException.getErrors().getHttpStatus().value()
        );
        return handleExceptionInternal(
                votacaoException,
                errorResponse,
                new HttpHeaders(),
                votacaoException.getErrors().getHttpStatus(),
                webRequest
        );
    }
}

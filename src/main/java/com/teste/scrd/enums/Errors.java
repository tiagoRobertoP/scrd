package com.teste.scrd.enums;

import org.springframework.http.HttpStatus;

public enum Errors {
    ASSEMBLEIA_NOT_FOUND("Assembleia not found", HttpStatus.NOT_FOUND),
    ASSOCIADO_NOT_FOUND("Associado not found", HttpStatus.NOT_FOUND),
    PAUTA_NOT_FOUND("Pauta not found", HttpStatus.NOT_FOUND),
    VOTACAO_NOT_FOUND("Votacao not found", HttpStatus.NOT_FOUND),
    VOTO_NOT_FOUND("Voto not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    Errors(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

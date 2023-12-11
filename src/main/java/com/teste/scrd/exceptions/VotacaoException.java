package com.teste.scrd.exceptions;

import com.teste.scrd.enums.Errors;

public class VotacaoException extends Exception{
    private final Errors errors;

    public VotacaoException(Errors errors){
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
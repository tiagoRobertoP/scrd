package com.teste.scrd.exceptions;

import com.teste.scrd.enums.Errors;

public class AssociadoException extends Exception{
    private final Errors errors;

    public AssociadoException(Errors errors){
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}

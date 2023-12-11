package com.teste.scrd.exceptions;

import com.teste.scrd.enums.Errors;

public class PautaException extends Exception{
    private final Errors errors;

    public PautaException(Errors errors){
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
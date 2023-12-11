package com.teste.scrd.exceptions;

import com.teste.scrd.enums.Errors;

public class VotoException extends Exception{
    private final Errors errors;

    public VotoException(Errors errors){
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}

package com.teste.scrd.exceptions;

import com.teste.scrd.enums.Errors;

public class AssembleiaException extends Exception{

    private final Errors errors;

    public AssembleiaException(Errors errors){
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}

package com.teste.scrd.dto;

public record ErrorResponse(
        String message,
        int statusCode
) {}

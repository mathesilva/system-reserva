package com.example.reservacode.exceptions;

public class EmailJaExiste extends RuntimeException {
    public EmailJaExiste(String message) {
        super(message);
    }
}

package com.example.backend.exceptions;

public class AuthException extends RuntimeException {
    public AuthException(String message){
        super(message);
    }
}

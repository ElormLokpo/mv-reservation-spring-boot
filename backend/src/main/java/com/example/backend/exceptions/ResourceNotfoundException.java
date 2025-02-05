package com.example.backend.exceptions;


public class ResourceNotfoundException extends RuntimeException {
    public ResourceNotfoundException(String message){
        super(message);
    }
    
}

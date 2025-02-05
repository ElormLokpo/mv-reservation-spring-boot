package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.backend.utils.ErrorResponseGen;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotfoundException.class)
    public ResponseEntity<ErrorResponseGen> ResourceNotFoundExceptionHandler(ResourceNotfoundException exception, WebRequest request){
        ErrorResponseGen errorResponse = ErrorResponseGen.builder()
        .statusCode(404)
        .message(exception.getMessage())
        .details(request.getDescription(false))
        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseGen> ErrorHandler(Exception exception, WebRequest request){
        ErrorResponseGen errorResponse = ErrorResponseGen.builder()
        .statusCode(500)
        .message(exception.getMessage())
        .details(request.getDescription(true))
        .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

}

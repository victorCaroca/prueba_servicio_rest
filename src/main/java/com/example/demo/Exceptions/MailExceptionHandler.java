package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MailExceptionHandler {

    public ResponseEntity<Object> handleMailException(MailException e){
           Exception newE= new Exception(
                    e.getMessage(),
                    e
            );
        return new ResponseEntity<>(newE, HttpStatus.BAD_REQUEST);
    }
}

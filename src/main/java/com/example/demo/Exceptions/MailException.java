package com.example.demo.Exceptions;

public class MailException extends Exception{

    public MailException(String message){
        super(message);
    }

    public MailException(String message, Throwable throwable){
        super(message, throwable);
    }
}

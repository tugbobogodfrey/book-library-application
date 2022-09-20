package com.book.library.application.exceptions;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 73263616501570402L;


    public DataNotFoundException(String message){
        super(message);
    }
}

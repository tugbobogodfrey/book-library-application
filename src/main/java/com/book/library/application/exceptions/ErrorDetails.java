package com.book.library.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private String message;
    private String details;
    private Date timestamp;

    public ErrorDetails(String message, Date timestamp){
        this.message = message;
        this.timestamp = timestamp;
    }

}

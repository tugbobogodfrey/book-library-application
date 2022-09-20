package com.book.library.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ApiErrorMessage {

    private String errorMessage;
    private String details;
    private Instant timeStamp;



}
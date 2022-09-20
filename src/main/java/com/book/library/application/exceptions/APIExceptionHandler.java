package com.book.library.application.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler( DataNotFoundException.class )
    public final ResponseEntity<?> handleBookException(DataNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(true), new Date());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( MethodNotAllowedException.class )
    public final ResponseEntity<?> handleBookException(MethodNotAllowedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(true), new Date());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        Instant timeStamp = Instant.now();
        ApiErrorMessage ApiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(), request.getDescription(true),
                timeStamp);
        return new ResponseEntity<Object>(ApiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails("Values passed not valid. Please check", request.getDescription(true), new Date());

        return new ResponseEntity(errorDetails, status);
    }

    /*@ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date());

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }*/

}

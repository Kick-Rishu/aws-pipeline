package com.caching.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * The type Custom exception response handler.
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionResponseHandler {

    /**
     * Handle api response parsing exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(ApiResponseParsingException.class)
    public ResponseEntity<String> handleApiResponseParsingException(ApiResponseParsingException e) {
        return ResponseEntity.noContent().build();
    }

    /**
     * Handle io exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException e) {
        return ResponseEntity.internalServerError().body("Error connecting to the API: " + e.getMessage());
    }

    /**
     * Handle interrupted exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<String> handleInterruptedException(InterruptedException e) {
        return ResponseEntity.internalServerError().body("Request was interrupted: " + e.getMessage());
    }

    /**
     * Handle method argument not valid exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation failed: {}", e.getParameter());
        return ResponseEntity.badRequest().body("Data validation failed for " + e.getParameter() + " !!");
    }

}

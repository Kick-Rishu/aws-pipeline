package com.caching.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Api response parsing exception.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "data is not available")
public class ApiResponseParsingException extends RuntimeException {
    /**
     * Instantiates a new Api response parsing exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public ApiResponseParsingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

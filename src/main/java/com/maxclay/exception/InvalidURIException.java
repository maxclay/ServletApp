package com.maxclay.exception;

/**
 * Represents exception that can be thrown in case of invalid format of processed URI.
 *
 * @author maxclay
 */
public class InvalidURIException extends Exception {

    public InvalidURIException() {
    }

    public InvalidURIException(String message) {
        super(message);
    }
}

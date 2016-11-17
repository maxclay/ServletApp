package com.maxclay.exception;

/**
 * @author maxclay
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String message) {

        super(message);
    }

}

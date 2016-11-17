package com.maxclay.util;

/**
 * Helper class which contains public constants that represent HTTP status codes.
 *
 * @author maxclay
 */
public final class HttpStatus {

    public static final int CREATED = 201;
    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;
    public static final int INTERNAL_SERVER_ERROR = 500;

    private HttpStatus() {
    }
}

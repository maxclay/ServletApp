package com.maxclay.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class which provides method for retrieving resource identifier from instance of servlet request.
 *
 * @author maxclay
 */
public final class RestRequestUtil {

    private static final Pattern ID_PATTERN = Pattern.compile("/([0-9]+)");

    private RestRequestUtil() {
    }

    /**
     * Retrieves resource identifier, that specified as path variable from given request object.
     *
     * @param request servlet request.
     * @return retrieved id of resource or 'null' if id is not specified(in case of '/' request).
     * @throws InvalidURIException thrown in case of invalid URI that does not represent resource identifier.
     */
    public static Long getId(HttpServletRequest request) throws InvalidURIException {

        if (request == null) {
            throw new IllegalArgumentException("Request could not be null");
        }

        String pathInfo = request.getPathInfo();
        if ("/".equals(pathInfo)) {
            return null;
        }

        Matcher matcher = ID_PATTERN.matcher(pathInfo);
        if (matcher.find()) {
            return Long.valueOf(matcher.group(1));
        }

        throw new InvalidURIException();
    }
}

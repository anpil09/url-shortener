package com.anpil.urlshortener.util;

/**
 * Contains utility methods for {@link String}.
 *
 * @author Andrei Pilipenka
 */
public final class StringUtil {

    private static final String SLASH = "/";

    private StringUtil() {
    }

    /**
     * Precedes given string with a slash ("/") character.
     *
     * @param string {@link String} to precede with a slash.
     * @return resulted {@link String}
     */
    public static String precedeWithSlash(String string) {
        return SLASH + string;
    }

}

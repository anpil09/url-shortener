package com.anpil.urlshortener.util;

public final class StringUtil {

    private static final String SLASH = "/";

    private StringUtil() {}

    public static String precedeWithSlash(String string) {
        return SLASH + string;
    }

}

package com.anpil.urlshortener.constant;

/**
 * Class containing error messages used by the application.
 *
 * @author Andrei Pilipenka
 */
public final class ErrorMessage {

    public static final String SEO_KEYWORD_TOO_LONG_MSG = "Seo keyword length is 21, but max allowed length is 20";
    public static final String SEO_KEYWORD_WRONG_FORMAT_MSG =
            "Invalid SEO keyword format. Allowed characters are: 'A-Z', 'a-z', '-'. SEO keyword cannot start/end with" +
                    " '-'";
    public static final String SEO_KEYWORD_CANNOT_BE_EMPTY_MSG = "SEO keyword cannot be empty";
    public static final String SEO_KEYWORD_CANNOT_BE_NULL_MSG = "SEO keyword cannot be null";
    public static final String URL_CANNOT_BE_NULL_MSG = "URL cannot be null";
    public static final String INVALID_URL_FORMAT_MSG = "URL format is invalid";
    public static final String URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG = "URL's file segment cannot be empty";
    private ErrorMessage() {
    }

}

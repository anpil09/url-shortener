package com.anpil.urlshortener.testsupport;

import java.net.MalformedURLException;
import java.net.URL;

public final class TestConstant {

    private static final String BASE_HOST = "looooong.com";

    public static final String VALID_SEO_KEYWORD = "12some-SEO-keyword34";
    public static final String PROTOCOL = "http";
    public static final String EXPECTED_HOST = "short.com";
    // Error messages
    public static final String SEO_KEYWORD_TOO_LONG_MSG = "Seo keyword length is 21, but max allowed length is 20";
    public static final String SEO_KEYWORD_WRONG_FORMAT_MSG =
            "Invalid SEO keyword format. Allowed characters are: 'A-Z', 'a-z', '-'. SEO keyword cannot start/end with" +
                    " '-'";
    public static final String SEO_KEYWORD_CANNOT_BE_EMPTY_MSG = "SEO keyword cannot be empty";
    public static final String SEO_KEYWORD_CANNOT_BE_NULL_MSG = "SEO keyword cannot be null";
    public static final String URL_CANNOT_BE_NULL_MSG = "URL cannot be null";
    public static final String URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG = "URL's file segment cannot be empty";
    private TestConstant() {
    }

    public static URL createUrl() throws MalformedURLException {
        return new URL(PROTOCOL, BASE_HOST, "/somepath");
    }

    public static URL createUrlWithEmptyFileSegment() throws MalformedURLException {
        return new URL(PROTOCOL, BASE_HOST, "");
    }

    public static URL createUrlWithBlankFileSegment() throws MalformedURLException {
        return new URL(PROTOCOL, BASE_HOST, " ");
    }

}

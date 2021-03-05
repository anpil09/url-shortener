package com.anpil.urlshortener.testsupport;

import com.anpil.urlshortener.url.builder.UrlBuilder;

public final class TestConstant {

    private static final String BASE_HOST = "looooong.com";

    public static final String VALID_SEO_KEYWORD = "12some-SEO-keyword34";
    public static final String PROTOCOL = "http";
    public static final String EXPECTED_HOST = "short.com";
    public static final String EXPECTED_PROTOCOL_AND_HOST = PROTOCOL + "://" + EXPECTED_HOST;
    public static final String INVALID_URL_STRING = "a";
    // Error messages
    public static final String SEO_KEYWORD_TOO_LONG_MSG = "Seo keyword length is 21, but max allowed length is 20";
    public static final String SEO_KEYWORD_WRONG_FORMAT_MSG =
            "Invalid SEO keyword format. Allowed characters are: 'A-Z', 'a-z', '-'. SEO keyword cannot start/end with" +
                    " '-'";
    public static final String SEO_KEYWORD_CANNOT_BE_EMPTY_MSG = "SEO keyword cannot be empty";
    public static final String SEO_KEYWORD_CANNOT_BE_NULL_MSG = "SEO keyword cannot be null";
    public static final String URL_CANNOT_BE_NULL_MSG = "URL cannot be null";
    public static final String URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG = "URL's file segment cannot be empty";
    public static final String INVALID_URL_FORMAT_MSG = "URL format is invalid";
    private TestConstant() {
    }

    public static String createUrlString() {
        return createBasicUrlBuilder().file("/somepath").build().toString();
    }

    public static String createUrlStringWithEmptyFileSegment() {
        return createBasicUrlBuilder().file("").build().toString();
    }

    public static String createUrlStringWithBlankFileSegment() {
        return createBasicUrlBuilder().file(" ").build().toString();
    }

    public static String createExpectedUrlString(String file) {
        return createBasicUrlBuilder().host(EXPECTED_HOST).file("/" + file).build().toString();
    }

    private static UrlBuilder createBasicUrlBuilder() {
        return new UrlBuilder().protocol(PROTOCOL).host(BASE_HOST);
    }

}

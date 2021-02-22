package com.anpil.urlshortener.constant;

public final class AppConfig {

    private AppConfig() {}

    public static final String SHORTENED_HOST = "short.com";

    public static final String SEO_KEYWORD_REGEX = "[A-Za-z0-9]([A-Za-z0-9\\-]*[A-Za-z0-9])?";

    public static final int MAX_SEO_KEYWORD_LENGTH = 20;

    public static final int RANDOM_PATH_LENGTH = 4;

}

package com.anpil.urlshortener.url;

import com.anpil.urlshortener.generator.RandomStringGenerator;
import com.anpil.urlshortener.url.builder.UrlBuilder;

import static java.util.Objects.requireNonNull;
import static com.anpil.urlshortener.constant.ErrorMessage.*;
import static com.anpil.urlshortener.constant.AppConfig.*;
import static com.anpil.urlshortener.util.StringUtil.*;

import java.net.URL;

/**
 * {@inheritDoc}
 */
public class UrlGeneratorImpl implements UrlGenerator {

    private final RandomStringGenerator stringGenerator = new RandomStringGenerator();

    @Override
    public URL generateBySeoKeyword(URL url, String seoKeyword) {
        checkParameters(url, seoKeyword);

        return UrlBuilder.from(url).host(SHORTENED_HOST).file(precedeWithSlash(seoKeyword)).build();
    }

    @Override
    public URL generateWithRandomPath(URL url) {
        checkURLNonNull(url);

        return UrlBuilder
                .from(url)
                .host(SHORTENED_HOST)
                .file(precedeWithSlash(stringGenerator.generate()))
                .build();
    }

    private void checkParameters(URL url, String seoKeyword) {
        checkURLNonNull(url);
        requireNonNull(seoKeyword, SEO_KEYWORD_CANNOT_BE_NULL_MSG);
        checkSeoKeywordFormat(seoKeyword);
    }

    private void checkURLNonNull(URL url) {
        requireNonNull(url, URL_CANNOT_BE_NULL_MSG);
    }

    private void checkSeoKeywordFormat(String seoKeyword) {
        if (seoKeyword.isBlank()) {
            throw new IllegalArgumentException(SEO_KEYWORD_CANNOT_BE_EMPTY_MSG);
        }

        if (seoKeyword.length() > MAX_SEO_KEYWORD_LENGTH) {
            throw new IllegalArgumentException(SEO_KEYWORD_TOO_LONG_MSG);
        }

        if (!seoKeyword.matches(SEO_KEYWORD_REGEX)) {
            throw new IllegalArgumentException(SEO_KEYWORD_WRONG_FORMAT_MSG);
        }
    }

}

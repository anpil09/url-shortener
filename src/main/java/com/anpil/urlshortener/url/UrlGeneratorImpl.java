package com.anpil.urlshortener.url;

import com.anpil.urlshortener.generator.RandomStringGenerator;
import com.anpil.urlshortener.url.builder.UrlBuilder;

import java.net.URL;

import static com.anpil.urlshortener.constant.AppConfig.*;
import static com.anpil.urlshortener.constant.ErrorMessage.*;
import static com.anpil.urlshortener.util.StringUtil.precedeWithSlash;
import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 *
 * @author Andrei Pilipenka
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
        checkURL(url);

        return UrlBuilder.from(url).host(SHORTENED_HOST).file(precedeWithSlash(stringGenerator.generate())).build();
    }

    /**
     * Checks parameters for the generateBySeoKeyword method (url and seoKeyword).
     *
     * @param url        url to check
     * @param seoKeyword SEO keyword to check
     */
    private void checkParameters(URL url, String seoKeyword) {
        checkURL(url);
        requireNonNull(seoKeyword, SEO_KEYWORD_CANNOT_BE_NULL_MSG);
        checkSeoKeywordFormat(seoKeyword);
    }

    /**
     * Checks the given {@link URL} to be not null and to contain non-empty and non-blank file segment.
     *
     * @param url {@link URL} to check
     */
    private void checkURL(URL url) {
        requireNonNull(url, URL_CANNOT_BE_NULL_MSG);
        if (url.getFile().isBlank()) {
            throw new IllegalArgumentException(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG);
        }
    }

    /**
     * Checks seoKeyword to be not null, valid length and contains only dash ("-") separated alphanumeric characters.
     *
     * @param seoKeyword SEO keyword to check
     */
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

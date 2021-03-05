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

    private final StringToUrlConverter urlConverter = new StringToUrlConverter();

    @Override
    public String generateBySeoKeyword(String urlString, String seoKeyword) {
        requireNonNull(seoKeyword, SEO_KEYWORD_CANNOT_BE_NULL_MSG);
        checkSeoKeywordFormat(seoKeyword);
        URL url = checkIfValidAndConvertToURL(urlString);

        return UrlBuilder.from(url).host(SHORTENED_HOST).file(precedeWithSlash(seoKeyword)).build().toString();
    }

    @Override
    public String generateWithRandomPath(String urlString) {
        URL url = checkIfValidAndConvertToURL(urlString);

        return UrlBuilder.from(url)
                .host(SHORTENED_HOST)
                .file(precedeWithSlash(stringGenerator.generate()))
                .build()
                .toString();
    }

    /**
     * Checks the format of the given {@link String} representing a URL and converts it to {@link URL}
     *
     * @param urlString string to check
     * @return converted {@link URL}
     */
    private URL checkIfValidAndConvertToURL(String urlString) {
        requireNonNull(urlString, URL_CANNOT_BE_NULL_MSG);

        URL url = urlConverter.convert(urlString);

        if (url.getFile().isBlank()) {
            throw new IllegalArgumentException(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG);
        }

        return url;
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

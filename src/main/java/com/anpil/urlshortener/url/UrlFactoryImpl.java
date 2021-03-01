package com.anpil.urlshortener.url;

import com.anpil.urlshortener.generator.SequentialNumberGenerator;
import com.anpil.urlshortener.url.builder.UrlBuilder;

import java.net.URL;

import static com.anpil.urlshortener.constant.AppConfig.SHORTENED_HOST;
import static com.anpil.urlshortener.constant.ErrorMessage.URL_CANNOT_BE_NULL_MSG;
import static com.anpil.urlshortener.constant.ErrorMessage.URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG;
import static com.anpil.urlshortener.util.StringUtil.precedeWithSlash;
import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 *
 * @author Andrei Pilipenka
 */
public class UrlFactoryImpl implements UrlFactory {

    private final SequentialNumberGenerator sequentialNumberGenerator = SequentialNumberGenerator.getInstance();

    @Override
    public URL createWithSequenceNumberPath(URL originalUrl) {
        checkURL(originalUrl);

        return UrlBuilder.from(originalUrl)
                .host(SHORTENED_HOST)
                .file(precedeWithSlash(String.valueOf(sequentialNumberGenerator.nextValue())))
                .build();
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

}

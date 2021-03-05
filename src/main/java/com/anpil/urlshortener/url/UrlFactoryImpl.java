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

    private final StringToUrlConverter urlConverter = new StringToUrlConverter();

    @Override
    public String createWithSequenceNumberPath(String urlString) {
        requireNonNull(urlString, URL_CANNOT_BE_NULL_MSG);
        URL url = urlConverter.convert(urlString);
        checkURL(url);

        return UrlBuilder.from(url)
                .host(SHORTENED_HOST)
                .file(precedeWithSlash(String.valueOf(sequentialNumberGenerator.nextValue())))
                .build().toString();
    }

    /**
     * Checks the given {@link URL} to be not null and to contain non-empty and non-blank file segment.
     *
     * @param url {@link URL} to check
     */
    private void checkURL(URL url) {
        if (url.getFile().isBlank()) {
            throw new IllegalArgumentException(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG);
        }
    }

}

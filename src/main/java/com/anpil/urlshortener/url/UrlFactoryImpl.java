package com.anpil.urlshortener.url;

import com.anpil.urlshortener.generator.SequentialNumberGenerator;
import com.anpil.urlshortener.url.builder.UrlBuilder;

import static java.util.Objects.requireNonNull;
import static com.anpil.urlshortener.constant.ErrorMessage.URL_CANNOT_BE_NULL_MSG;
import static com.anpil.urlshortener.constant.AppConfig.SHORTENED_HOST;
import static com.anpil.urlshortener.util.StringUtil.*;

import java.net.URL;

/**
 * {@inheritDoc}
 */
public class UrlFactoryImpl implements UrlFactory {

    private final SequentialNumberGenerator sequentialNumberGenerator = SequentialNumberGenerator.getInstance();

    @Override
    public URL createWithSequenceNumberPath(URL originalUrl) {
        requireNonNull(originalUrl, URL_CANNOT_BE_NULL_MSG);

        return UrlBuilder
                .from(originalUrl)
                .host(SHORTENED_HOST)
                .file(precedeWithSlash(String.valueOf(sequentialNumberGenerator.nextValue())))
                .build();
    }

}

package com.anpil.urlshortener.url;

import java.net.MalformedURLException;
import java.net.URL;

import static com.anpil.urlshortener.constant.ErrorMessage.INVALID_URL_FORMAT_MSG;

/**
 * Class to convert {@link String} to {@link URL}.
 *
 * @author Andrei Pilipenka
 */
class StringToUrlConverter {

    /**
     * Converts {@link String} to {@link URL}
     *
     * @param urlString String to be converted
     * @return resulted URL
     * @throws IllegalArgumentException if urlString has invalid format
     */
    URL convert(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(INVALID_URL_FORMAT_MSG, e);
        }
    }

}

package com.anpil.urlshortener.url;

import java.net.URL;

/**
 * Encapsulates methods to create shortened {@link URL}'s.
 *
 * @author Andrei Pilipenka
 */
public interface UrlGenerator {

    /**
     * Generates shortened URL by replacing original host with a short host and file with a seoKeyword.
     *
     * @param url        Original in {@link String} form to shorten
     * @param seoKeyword Alphanumeric phrase to replace URL's file with. "-" characters are allowed to
     *                   separate separate words in the seoKeyword
     * @return Shortened URL
     */
    String generateBySeoKeyword(String url, String seoKeyword);

    /**
     * Generates shortened URL by replacing original file with a sequence of 4 random alphanumeric characters
     *
     * @param url Original URL
     * @return Shortened URL
     */
    String generateWithRandomPath(String url);

}

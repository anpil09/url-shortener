package com.anpil.urlshortener.url;

import java.net.URL;

/**
 * Encapsulates methods to create shortened {@link URL}'s.
 */
public interface UrlGenerator {

    /**
     * Generates shortened {@link URL} by replacing original host with a short host and file with a seoKeyword.
     *
     * @param url
     *          Original {@link URL} to shorten
     * @param seoKeyword
     *          Alphanumeric phrase to replace {@link URL}'s file with. "-" characters are allowed to separate separate words in the seoKeyword
     * @return
     *          Shortened {@link URL}
     */
    URL generateBySeoKeyword(URL url, String seoKeyword);

    /**
     * Generates shortened {@link URL} by replacing original file with a sequence of 4 random alphanumeric characters
     *
     * @param url
     *          Original {@link URL}
     * @return
     *          Shortened {@link URL}
     */
    URL generateWithRandomPath(URL url);

}

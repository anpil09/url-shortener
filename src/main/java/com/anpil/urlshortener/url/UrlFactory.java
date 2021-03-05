package com.anpil.urlshortener.url;

import java.net.URL;

/**
 * Factory to create shortened {@link URL}.
 *
 * @author Andrei Pilipenka
 */
public interface UrlFactory {

    /**
     * Creates shortened URL with a file as a member of a monotonically increasing sequence of integers.
     *
     * @param urlString Original URL
     * @return Shortened URL
     */
    String createWithSequenceNumberPath(String urlString);

}

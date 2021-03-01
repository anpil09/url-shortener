package com.anpil.urlshortener.url;

import java.net.URL;

/**
 * Factory to create shortened {@link URL}.
 *
 * @author Andrei Pilipenka
 */
public interface UrlFactory {

    /**
     * Creates shortened {@link URL} with a file as a member of a monotonically increasing sequence of integers.
     *
     * @param originalUrl Original {@link URL}
     * @return Shortened {@link URL}
     */
    URL createWithSequenceNumberPath(URL originalUrl);

}

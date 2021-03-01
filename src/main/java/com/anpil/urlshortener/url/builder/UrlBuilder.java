package com.anpil.urlshortener.url.builder;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Builder for {@link URL}.
 */
public class UrlBuilder {

    private String protocol;
    private String host;
    private String file;

    /**
     * Creates a builder instance using fields of a given {@link URL}.
     *
     * @param url {@link URL} with fields used to create a {@link UrlBuilder} instance
     * @return Instance of {@link UrlBuilder}
     */
    public static UrlBuilder from(URL url) {
        UrlBuilder builder = new UrlBuilder();
        builder.protocol = url.getProtocol();
        builder.host = url.getHost();
        builder.file = url.getFile();
        return builder;
    }

    public UrlBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public UrlBuilder host(String host) {
        this.host = host;
        return this;
    }

    public UrlBuilder file(String file) {
        this.file = file;
        return this;
    }

    /**
     * Creates a {@link URL} with the fields of this {@link UrlBuilder}
     *
     * @return Created {@link URL}
     * @throws RuntimeException when an error occurred creating the {@link URL}
     */
    public URL build() {
        try {
            return new URL(protocol, host, file);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error creating URL", e);
        }
    }

}

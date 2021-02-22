package com.anpil.urlshortener.url.builder;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilder {

    private String protocol;
    private String host;
    private String file;

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

    public URL build() {
        try {
            return new URL(protocol, host, file);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error creating URL", e);
        }
    }

}

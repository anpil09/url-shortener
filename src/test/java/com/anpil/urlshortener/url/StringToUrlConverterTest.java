package com.anpil.urlshortener.url;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.anpil.urlshortener.testsupport.TestConstant.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringToUrlConverterTest {

    private StringToUrlConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new StringToUrlConverter();
    }

    @Test
    public void shouldReturnUrl_whenConvert_givenValidUrlString() throws MalformedURLException {
        // given
        String urlString = createUrlString();
        URL expectedUrl = new URL(urlString);

        // when
        URL url = converter.convert(urlString);

        // then
        assertEquals(expectedUrl, url);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithInvalidUrlMsg_whenConvert_givenInvalidStringWithInvalidUrl() {
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> converter.convert(INVALID_URL_STRING));

        // then
        assertEquals(INVALID_URL_FORMAT_MSG, exception.getMessage());
        assertEquals(MalformedURLException.class, exception.getCause().getClass());
    }

}

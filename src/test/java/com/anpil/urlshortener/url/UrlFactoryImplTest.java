package com.anpil.urlshortener.url;

import static com.anpil.urlshortener.testsupport.TestUtil.*;
import static com.anpil.urlshortener.testsupport.TestConstant.createUrl;

import com.anpil.urlshortener.testsupport.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UrlFactoryImplTest {

    private static final int NUMBER_OF_INVOCATIONS = 10;

    private static final String URL_CANNOT_BE_NULL_MSG = "URL cannot be null";
    private UrlFactoryImpl urlFactory;

    @BeforeEach
    public void setUp() {
        urlFactory = new UrlFactoryImpl();
    }

    @Test
    public void shouldThrowNullPointerException_whenCreateWithSequenceNumberPath_givenNothing() {
        // given

        // when
        NullPointerException exception = assertThrows(NullPointerException.class, () -> urlFactory.createWithSequenceNumberPath(null));

        // then
        assertEquals(URL_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldReturnNumbersBelongingToSequence_whenNextValue_givenNothing() throws MalformedURLException {
        // given
        URL givenUrl = createUrl();

        // when
        List<URL> resultedList =
                Stream.generate(
                        () -> urlFactory.createWithSequenceNumberPath(givenUrl))
                        .limit(NUMBER_OF_INVOCATIONS)
                        .collect(Collectors.toList());

        // then
        resultedList.forEach(TestUtil::checkProtocolAndHost);
        checkNumberSequence(resultedList.stream().map(url -> Long.valueOf(url.getFile().substring(1))).collect(Collectors.toList()));
    }

}
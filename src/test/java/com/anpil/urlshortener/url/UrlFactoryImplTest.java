package com.anpil.urlshortener.url;

import com.anpil.urlshortener.testsupport.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.anpil.urlshortener.testsupport.TestConstant.*;
import static com.anpil.urlshortener.testsupport.TestUtil.checkNumberSequence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UrlFactoryImplTest {

    private static final int NUMBER_OF_INVOCATIONS = 10;

    private static final String URL_CANNOT_BE_NULL_MSG = "URL cannot be null";
    private UrlFactoryImpl urlFactory;

    @BeforeEach
    public void setUp() {
        urlFactory = new UrlFactoryImpl();
    }

    @Test
    public void shouldThrowNullPointerException_whenCreateWithSequenceNumberPath_givenNullUrl() {
        // when
        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> urlFactory.createWithSequenceNumberPath(null));

        // then
        assertEquals(URL_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldReturnNumbersBelongingToSequence_whenCreateWithSequenceNumberPath_givenValidUrl() throws MalformedURLException {
        // given
        URL givenUrl = createUrl();

        // when
        List<URL> resultedList = Stream.generate(() -> urlFactory.createWithSequenceNumberPath(givenUrl))
                .limit(NUMBER_OF_INVOCATIONS)
                .collect(Collectors.toList());

        // then
        resultedList.forEach(TestUtil::checkProtocolAndHost);
        checkNumberSequence(resultedList.stream()
                .map(url -> Long.valueOf(url.getFile().substring(1)))
                .collect(Collectors.toList()));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenCreateWithSequenceNumberPath_givenUrlWithEmptyFileSegment()
            throws MalformedURLException {
        // given
        URL url = createUrlWithEmptyFileSegment();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlFactory.createWithSequenceNumberPath(url));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenCreateWithSequenceNumberPath_givenUrlWithBlankFileSegment()
            throws MalformedURLException {
        // given
        URL url = createUrlWithBlankFileSegment();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlFactory.createWithSequenceNumberPath(url));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

}
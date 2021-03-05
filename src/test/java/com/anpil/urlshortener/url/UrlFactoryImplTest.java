package com.anpil.urlshortener.url;

import com.anpil.urlshortener.testsupport.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.anpil.urlshortener.testsupport.TestConstant.*;
import static com.anpil.urlshortener.testsupport.TestUtil.checkNumberSequence;
import static com.anpil.urlshortener.testsupport.TestUtil.extractFilePart;
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
    public void shouldReturnNumbersBelongingToSequence_whenCreateWithSequenceNumberPath_givenValidUrl() {
        // given
        String givenUrl = createUrlString();

        // when
        List<String> resultedList = Stream.generate(() -> urlFactory.createWithSequenceNumberPath(givenUrl))
                .limit(NUMBER_OF_INVOCATIONS)
                .collect(Collectors.toList());

        // then
        resultedList.forEach(TestUtil::checkProtocolAndHost);
        checkNumberSequence(resultedList.stream()
                .map(url -> Long.valueOf(extractFilePart(url)))
                .collect(Collectors.toList()));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenCreateWithSequenceNumberPath_givenUrlWithEmptyFileSegment() {
        // given
        String url = createUrlStringWithEmptyFileSegment();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlFactory.createWithSequenceNumberPath(url));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithInvalidUrlMsg_whenGenerate_givenInvalidUrl() {
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlFactory.createWithSequenceNumberPath(INVALID_URL_STRING));

        // then
        assertEquals(INVALID_URL_FORMAT_MSG, exception.getMessage());
        assertEquals(MalformedURLException.class, exception.getCause().getClass());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenCreateWithSequenceNumberPath_givenUrlWithBlankFileSegment() {
        // given
        String url = createUrlStringWithBlankFileSegment();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlFactory.createWithSequenceNumberPath(url));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

}
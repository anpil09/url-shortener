package com.anpil.urlshortener.url;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static com.anpil.urlshortener.testsupport.TestConstant.*;
import static com.anpil.urlshortener.testsupport.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class UrlGeneratorImplTest {

    private static String givenUrlString;
    private UrlGeneratorImpl urlGenerator;

    @BeforeAll
    public static void setUpClass() {
        givenUrlString = createUrlString();
    }

    @BeforeEach
    public void setUp() {
        urlGenerator = new UrlGeneratorImpl();
    }

    @Test
    public void shouldReturnGeneratedSeoUrl_whenGenerate_givenValidUrlAndSeoKeywordOfProperLength() {
        // given
        String expectedUrl = createExpectedUrlString(VALID_SEO_KEYWORD);

        // when
        String actualUrl = urlGenerator.generateBySeoKeyword(givenUrlString, VALID_SEO_KEYWORD);

        // then
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWithSeoKeywordTooLongMsg_whenGenerate_givenValidUrlAndTooLongSeoKeyword() {
        // given
        String seoKeyword = "12some-SEO-keyword345";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_TOO_LONG_MSG, exception.getMessage());
    }

    @Test
    public void shouldReturnGeneratedSeoUrl_whenGenerate_givenValidUrlAndSeoKeywordWithOneCharacter() {
        // given
        String seoKeyword = "a";
        String expectedUrl = createExpectedUrlString(seoKeyword);

        // when
        String actualUrl = urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword);

        // then
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordCannotBeEmptyMsg_whenGenerate_givenValidUrlAndEmptySeoKeyword() {
        // given
        String seoKeyword = "";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordCannotBeEmptyMsg_whenGenerate_givenValidUrlAndBlankSeoKeyword() {
        // given
        String seoKeyword = " ";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordWrongFormatMsg_whenGenerate_givenValidUrlAndSeoKeywordStartingWithDash() {
        // given
        String seoKeyword = "-abc";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_WRONG_FORMAT_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordWrongFormatMsg_whenGenerate_givenValidUrlAndSeoKeywordEndingWithDash() {
        // given
        String seoKeyword = "abc-";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_WRONG_FORMAT_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordWrongFormatMsg_whenGenerate_givenValidUrlAndSeoKeywordWithInvalidSymbols() {
        // given
        String seoKeyword = "SEO keyword";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(givenUrlString, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_WRONG_FORMAT_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWithSeoKeywordCannotBeNullMsg_whenGenerate_givenValidUrlAndNullSeoKeyword() {
        // when
        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> urlGenerator.generateBySeoKeyword(givenUrlString, null));

        // then
        assertEquals(SEO_KEYWORD_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWithUrlCannotBeNullMsg_whenGenerate_givenNullUrlAndValidSeoKeyword() {
        // when
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> urlGenerator.generateBySeoKeyword(null, VALID_SEO_KEYWORD));

        // then
        assertEquals(URL_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithInvalidUrlMsg_whenGenerate_givenInvalidUrl() {
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(INVALID_URL_STRING, VALID_SEO_KEYWORD));

        // then
        assertEquals(INVALID_URL_FORMAT_MSG, exception.getMessage());
        assertEquals(MalformedURLException.class, exception.getCause().getClass());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenGenerate_givenUrlWithEmptyFileSegment() {
        // given
        String url = createUrlStringWithEmptyFileSegment();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(url, VALID_SEO_KEYWORD));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenGenerate_givenUrlWithBlankFileSegment() {
        // given
        String url = createUrlStringWithBlankFileSegment();

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateBySeoKeyword(url, VALID_SEO_KEYWORD));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWithUrlCannotBeNullMsg_whenGenerateWithRandomPath_givenNullUrl() {
        // when
        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> urlGenerator.generateWithRandomPath(null));

        // then
        assertEquals(URL_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldReturnNonRepeatableAlphanumericStringsOfValidLength_whenGenerateWithRandomPath_givenValidUrl() {
        // when
        String firstInvocationResult = urlGenerator.generateWithRandomPath(givenUrlString);
        String secondInvocationResult = urlGenerator.generateWithRandomPath(givenUrlString);

        // then
        checkProtocolAndHost(firstInvocationResult);
        checkProtocolAndHost(secondInvocationResult);
        String firstInvocationResultPath = extractFilePart(firstInvocationResult);
        String secondInvocationResultPath = extractFilePart(secondInvocationResult);
        checkAlphanumericString(firstInvocationResultPath);
        checkAlphanumericString(secondInvocationResultPath);
        assertNotEquals(firstInvocationResultPath, secondInvocationResultPath);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithInvalidUrlMsg_whenGenerateWithRandomPath_givenInvalidUrl() {
        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> urlGenerator.generateWithRandomPath(INVALID_URL_STRING));

        // then
        assertEquals(INVALID_URL_FORMAT_MSG, exception.getMessage());
        assertEquals(MalformedURLException.class, exception.getCause().getClass());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenGenerateWithRandomPath_givenUrlWithEmptyFileSegment() {
        // given
        String url = createUrlStringWithEmptyFileSegment();

        // when
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateWithRandomPath(url));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithUrlFileSegmentCannotBeEmptyMsg_whenGenerateWithRandomPath_givenUrlWithBlankFileSegment() {
        // given
        String url = createUrlStringWithBlankFileSegment();

        // when
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateWithRandomPath(url));

        // then
        assertEquals(URL_FILE_SEGMENT_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

}

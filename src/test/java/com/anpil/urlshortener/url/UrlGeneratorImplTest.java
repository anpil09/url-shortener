package com.anpil.urlshortener.url;

import static org.junit.jupiter.api.Assertions.*;
import static com.anpil.urlshortener.testsupport.TestUtil.*;
import static com.anpil.urlshortener.testsupport.TestConstant.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlGeneratorImplTest {

    private UrlGeneratorImpl urlGenerator;

    private static URL givenUrl;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        urlGenerator = new UrlGeneratorImpl();
        givenUrl = createUrl();
    }

    @Test
    public void shouldReturnGeneratedSeoUrl_whenGenerate_givenValidUrlAndSeoKeywordOfProperLength() throws MalformedURLException {
        // given
        URL expectedUrl = new URL("http", EXPECTED_HOST, "/" + VALID_SEO_KEYWORD);

        // when
        URL actualUrl = urlGenerator.generateBySeoKeyword(givenUrl, VALID_SEO_KEYWORD);

        // then
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWithSeoKeywordTooLongMsg_whenGenerate_givenValidUrlAndTooLongSeoKeyword() {
        // given
        String seoKeyword = "12some-SEO-keyword345";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_TOO_LONG_MSG, exception.getMessage());
    }

    @Test
    public void shouldReturnGeneratedSeoUrl_whenGenerate_givenValidUrlAndSeoKeywordWithOneCharacter() throws MalformedURLException {
        // given
        String seoKeyword = "a";
        URL expectedUrl = new URL("http", EXPECTED_HOST, "/" + seoKeyword);

        // when
        URL actualUrl = urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword);

        // then
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordCannotBeEmptyMsg_whenGenerate_givenValidUrlAndEmptySeoKeyword() {
        // given
        String seoKeyword = "";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordCannotBeEmptyMsg_whenGenerate_givenValidUrlAndBlankSeoKeyword() {
        // given
        String seoKeyword = " ";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_CANNOT_BE_EMPTY_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordWrongFormatMsg_whenGenerate_givenValidUrlAndSeoKeywordStartingWithDash() {
        // given
        String seoKeyword = "-abc";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_WRONG_FORMAT_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordWrongFormatMsg_whenGenerate_givenValidUrlAndSeoKeywordEndingWithDash() {
        // given
        String seoKeyword = "abc-";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_WRONG_FORMAT_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithSeoKeywordWrongFormatMsg_whenGenerate_givenValidUrlAndSeoKeywordWithInvalidSymbols() {
        // given
        String seoKeyword = "SEO keyword";

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, seoKeyword));

        // then
        assertEquals(SEO_KEYWORD_WRONG_FORMAT_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWithSeoKeywordCannotBeNullMsg_whenGenerate_givenValidUrlAndNullSeoKeyword() {
        // given

        // when
        NullPointerException exception = assertThrows(NullPointerException.class, () -> urlGenerator.generateBySeoKeyword(givenUrl, null));

        // then
        assertEquals(SEO_KEYWORD_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWithUrlCannotBeNullMsg_whenGenerate_givenNullUrlAndValidSeoKeyword() {
        // given

        // when
        NullPointerException exception = assertThrows(NullPointerException.class, () -> urlGenerator.generateBySeoKeyword(null, VALID_SEO_KEYWORD));

        // then
        assertEquals(URL_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWithUrlCannotBeNullMsg_whenGenerateWithRandomPath_givenNullUrl() {
        // given

        // when
        NullPointerException exception = assertThrows(NullPointerException.class, () -> urlGenerator.generateWithRandomPath(null));

        // then
        assertEquals(URL_CANNOT_BE_NULL_MSG, exception.getMessage());
    }

    @Test
    public void shouldReturnNonRepeatableAlphanumericStringsOfValidLength_whenGenerateWithRandomPath_givenNothing() {
        // given

        // when
        URL firstInvocationResult = urlGenerator.generateWithRandomPath(givenUrl);
        URL secondInvocationResult = urlGenerator.generateWithRandomPath(givenUrl);

        // then
        checkProtocolAndHost(firstInvocationResult);
        checkProtocolAndHost(secondInvocationResult);
        String firstInvocationResultPath = firstInvocationResult.getFile().substring(1); // without "/" at the beginning
        String secondInvocationResultPath = secondInvocationResult.getFile().substring(1); // without "/" at the beginning
        checkAlphanumericString(firstInvocationResultPath);
        checkAlphanumericString(secondInvocationResultPath);
        assertNotEquals(firstInvocationResultPath, secondInvocationResultPath);
    }

}

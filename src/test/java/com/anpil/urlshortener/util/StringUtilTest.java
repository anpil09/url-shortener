package com.anpil.urlshortener.util;

import org.junit.Test;

import static com.anpil.urlshortener.util.StringUtil.precedeWithSlash;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {

    @Test
    public void shouldReturnOriginalStringPrecededWithSlash_whenPrecedeWithString_givenString() {
        // given
        String originalString = "abc";
        String expectedString = "/abc";

        // when
        String resultedString = precedeWithSlash(originalString);

        // then
        assertEquals(expectedString, resultedString);
    }

}
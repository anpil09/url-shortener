package com.anpil.urlshortener.util;

import static com.anpil.urlshortener.util.StringUtil.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

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
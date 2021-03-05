package com.anpil.urlshortener.testsupport;

import java.util.List;

import static com.anpil.urlshortener.testsupport.TestConstant.EXPECTED_PROTOCOL_AND_HOST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtil {

    private static final int EXPECTED_STRING_LENGTH = 4;

    private static final char SLASH = '/';

    public static void checkAlphanumericString(String result) {
        assertEquals(EXPECTED_STRING_LENGTH, result.length());
        for (char character : result.toCharArray()) {
            assertTrue(isAsciiLetterOrDigit(character));
        }
    }

    public static void checkNumberSequence(List<Long> resultedList) {
        for (int i = 1; i < resultedList.size(); i++) {
            assertEquals(1, resultedList.get(i) - resultedList.get(i - 1));
        }
    }

    public static void checkProtocolAndHost(String url) {
        assertTrue(url.startsWith(EXPECTED_PROTOCOL_AND_HOST));
    }

    public static String extractFilePart(String url) {
        return url.substring(url.lastIndexOf(SLASH) + 1);
    }

    private static boolean isAsciiLetterOrDigit(char character) {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') ||
                (character >= '0' && character <= '9');
    }

}

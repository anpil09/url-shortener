package com.anpil.urlshortener.testsupport;

import java.net.URL;
import java.util.List;

import static com.anpil.urlshortener.testsupport.TestConstant.EXPECTED_HOST;
import static com.anpil.urlshortener.testsupport.TestConstant.PROTOCOL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtil {

    private static final int EXPECTED_STRING_LENGTH = 4;

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

    private static boolean isAsciiLetterOrDigit(char character) {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') ||
                (character >= '0' && character <= '9');
    }

    public static void checkProtocolAndHost(URL result) {
        assertEquals(PROTOCOL, result.getProtocol());
        assertEquals(EXPECTED_HOST, result.getHost());
    }

}

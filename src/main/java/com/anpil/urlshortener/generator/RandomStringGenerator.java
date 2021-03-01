package com.anpil.urlshortener.generator;

import java.util.Random;

import static com.anpil.urlshortener.constant.AppConfig.RANDOM_PATH_LENGTH;

/**
 * Generator of a random ASCII alphanumeric strings.
 *
 * @author Andrei Pilipenka
 */
public class RandomStringGenerator {

    private static final char[] ALPHANUMERIC_CHARS =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                    'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', '0'};

    private final Random random = new Random();

    /**
     * Generates a random ASCII alphanumeric string with length of 4.
     *
     * @return Generated string
     */
    public String generate() {
        return random.ints(0, ALPHANUMERIC_CHARS.length - 1)
                .map(i -> ALPHANUMERIC_CHARS[i])
                .limit(RANDOM_PATH_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}

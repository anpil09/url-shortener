package com.anpil.urlshortener.generator;

import static com.anpil.urlshortener.constant.AppConfig.RANDOM_PATH_LENGTH;

import java.util.Random;

/**
 * Generator of a random ASCII alphanumeric strings.
 */
public class RandomStringGenerator {

    private static final int ASCII_CODE_OF_0 = 48;
    private static final int ASCII_CODE_OF_LOWERCASE_Z = 122;
    private static final int ASCII_CODE_OF_9 = 57;
    private static final int ASCII_CODE_OF_CAPITAL_A = 65;
    private static final int ASCII_CODE_OF_CAPITAL_Z = 90;
    private static final int ASCII_CODE_OF_LOWERCASE_A = 97;

    private final Random random = new Random();

    /**
     * Generates a random ASCII alphanumeric string with length of 4.
     *
     * @return
     *          Generated string
     */
    public String generate() {
        return random.ints(ASCII_CODE_OF_0, ASCII_CODE_OF_LOWERCASE_Z + 1)
                .filter(i ->
                        (i <= ASCII_CODE_OF_9 || i >= ASCII_CODE_OF_CAPITAL_A)
                                && (i <= ASCII_CODE_OF_CAPITAL_Z || i >= ASCII_CODE_OF_LOWERCASE_A))
                .limit(RANDOM_PATH_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}

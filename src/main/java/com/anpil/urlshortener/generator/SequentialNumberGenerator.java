package com.anpil.urlshortener.generator;

/**
 * Generates a numbers of a monotonically increasing sequence of integers.
 *
 * @author Andrei Pilipenka
 */
public final class SequentialNumberGenerator {

    private static final SequentialNumberGenerator INSTANCE = new SequentialNumberGenerator();
    private long lastValue = 0;

    private SequentialNumberGenerator() {
    }

    /**
     * Method to get the instance of the class.
     *
     * @return Instance of the class
     */
    public static synchronized SequentialNumberGenerator getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the next value of a sequence.
     *
     * @return The next value of a sequence
     */
    public synchronized long nextValue() {
        return lastValue++;
    }

}

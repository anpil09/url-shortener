package com.anpil.urlshortener.generator;

/**
 * Generates a numbers of a monotonically increasing sequence of integers.
 */
public final class SequentialNumberGenerator {

    private long lastValue = 0;

    private static final SequentialNumberGenerator INSTANCE = new SequentialNumberGenerator();

    private SequentialNumberGenerator() {}

    public static synchronized SequentialNumberGenerator getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the next value of a sequence.
     *
     * @return
     *          The next value of a sequence
     */
    public synchronized long nextValue() {
        return lastValue++;
    }

}

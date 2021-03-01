package com.anpil.urlshortener.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.anpil.urlshortener.testsupport.TestUtil.checkAlphanumericString;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RandomStringGeneratorTest {

    private static final int EXPECTED_STRING_LENGTH = 4;

    private RandomStringGenerator stringGenerator;

    @BeforeEach
    public void setUp() {
        stringGenerator = new RandomStringGenerator();
    }

    @Test
    public void shouldReturnNonRepeatableAlphanumericStringsOfValidLength_whenGenerate_givenNothing() {
        // given

        // when
        String firstInvocationResult = stringGenerator.generate();
        String secondInvocationResult = stringGenerator.generate();

        // when
        checkAlphanumericString(firstInvocationResult);
        checkAlphanumericString(secondInvocationResult);
        assertNotEquals(firstInvocationResult, secondInvocationResult);
    }

}
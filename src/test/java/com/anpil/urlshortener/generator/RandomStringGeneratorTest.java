package com.anpil.urlshortener.generator;

import static com.anpil.urlshortener.testsupport.TestUtil.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

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
package com.anpil.urlshortener.generator;

import static com.anpil.urlshortener.testsupport.TestUtil.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SequentialNumberGeneratorTest {

    private static final int NUMBER_OF_INVOCATIONS = 10;

    private final SequentialNumberGenerator sequentialNumberGenerator = SequentialNumberGenerator.getInstance();

    @Test
    public void shouldReturnNumbersBelongingToSequence_whenNextValue_givenNothing() {
        // given

        // when
        List<Long> resultedList =
                Stream.generate(
                        sequentialNumberGenerator::nextValue)
                        .limit(NUMBER_OF_INVOCATIONS)
                        .collect(Collectors.toList());

        // then
        checkNumberSequence(resultedList);
    }

}
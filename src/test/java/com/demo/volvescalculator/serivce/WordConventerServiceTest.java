package com.demo.volvescalculator.serivce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordConventerServiceTest {

    @ParameterizedTest
    @MethodSource("parametersForShouldConvertWordsToArray")
    void shouldConvertWordsToArray(String input, String[] expectedResult) {
        // given
        WordConventerService wordConventerService = new WordConventerService();

        // when
        String[] tests = wordConventerService.convertWordsToArray(input);

        // then
        assertArrayEquals(tests, expectedResult);
    }

    private static Stream<Arguments> parametersForShouldConvertWordsToArray() {
        return Stream.of(
                Arguments.of("test for words", new String[]{"test", "for", "words"}),
                Arguments.of("examination", new String[]{"examination"}),
                Arguments.of("", new String[]{""})
        );
    }
}
package com.demo.volvescalculator.serivce;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MapConventerServiceServiceTest {

    private final MapConventerServiceService mapConventerServiceService = new MapConventerServiceService();

    @ParameterizedTest
    @MethodSource("parametersForShouldGetVowelMap")
    void shouldGetVowelMap(String input, Map<Character, Long> expectedMap) {
        // when
        Map<Character, Long> vowelMap = mapConventerServiceService.getVowelMap(input);

        // then
        assertEquals(vowelMap, expectedMap);
    }

    private static Stream<Arguments> parametersForShouldGetVowelMap() {
        return Stream.of(
                Arguments.of("test", Map.of('e', 1L)),
                Arguments.of("examination", Map.of('a',2L, 'e', 1L, 'i' , 2L, 'o',1L)),
                Arguments.of("", Map.of()),
                Arguments.of("ccccc", Map.of()),
                Arguments.of("\\s", Map.of())
        );
    }

}
package com.demo.volvescalculator.serivce;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

class WordServiceTest {

    private final WordService wordService = new WordService();

    @Test
    void test() {
        // given
        String input= "testeee";

        // then
        Map<Character, Long> characterLongMap = wordService.characterMap(input);
        System.out.println(characterLongMap);
    }

    @Test
    void shouldFilterOutNonVolwesElements() {
        //given
        Map<Character, Long> characterMap = ImmutableMap.<Character, Long>builder()
                .put('a',2L)
                .put('t',5L)
                .put('u',3L)
                .build();

        //when
       // Map<Character, Long> volwesMap = wordService.vowelsMap(characterMap);

        //then
      //  System.out.println(volwesMap);
    }
}
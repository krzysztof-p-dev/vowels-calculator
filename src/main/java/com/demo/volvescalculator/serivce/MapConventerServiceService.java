package com.demo.volvescalculator.serivce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapConventerServiceService {

    private static final String[] VOWELS = {"a", "e", "i", "o", "u"};

    public Map<Character, Long> getVowelMap(String word) {
        return convertWordToCharacter(word)
                .entrySet()
                .stream()
                .filter(this::isCharVowel)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean isCharVowel(Map.Entry<Character, Long> value) {
        return Arrays.stream(VOWELS)
                .anyMatch(vowel -> vowel.equalsIgnoreCase(String.valueOf(value.getKey())));
    }

    private Map<Character, Long> convertWordToCharacter(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}

package com.demo.volvescalculator.serivce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VowelsCalculatorService {

    private static final String[] VOWELS = {"a", "e", "i", "o", "u"};

    private final WordService wordService;

    Map<Character, Long> vowelMap;

    public static boolean isCharVowel(char ch) {
        return Arrays.stream(VOWELS).anyMatch(vowel -> vowel.equalsIgnoreCase(String.valueOf(ch)));
    }

    public Map<Character, Long> getVowelMap(String word) {
        vowelMap = wordService.characterMap(word);
        return filterOutNonVowelElements(vowelMap);
    }

    private Map<Character, Long> filterOutNonVowelElements(Map<Character, Long> characterMap) {
        return characterMap.entrySet()
                .stream()
                .filter(WordService::isKeyVowel)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

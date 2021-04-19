package com.demo.volvescalculator.serivce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordService {

    private static final Pattern PATTERN = Pattern.compile(" ");
    private static final Pattern ALPHABET_PATTERN = Pattern.compile("[^a-zA-Z0-9]");

    public Map<Character, Long> characterMap(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    static boolean isKeyVowel(Map.Entry<Character, Long> ch) {
        return VowelsCalculatorService.isCharVowel(ch.getKey());
    }

    public String[] splitWordsToArray(String text) {
        return PATTERN.splitAsStream(text)
                .map(String::trim)
                .toArray(String[]::new);
    }

    public String removeNonAlphabeticCharacters(String word) {
        return ALPHABET_PATTERN.matcher(word).replaceAll("");
    }

    public String[] getWordsArray(String text) {
        String allWords = String.join("", text);
        return splitWordsToArray(allWords);
    }

    public String[] convertWords(String[] words) {
        return Arrays.stream(words)
                .map(this::removeNonAlphabeticCharacters)
                .map(word -> word.toLowerCase(Locale.ROOT))
                .toArray(String[]::new);
    }
}

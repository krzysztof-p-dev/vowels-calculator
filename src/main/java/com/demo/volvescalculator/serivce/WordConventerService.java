package com.demo.volvescalculator.serivce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WordConventerService {

    private static final Pattern PATTERN = Pattern.compile(" ");
    private static final Pattern ALPHABET_PATTERN = Pattern.compile("[^a-zA-Z0-9]");

    public String[] convertWordsToArray(String text) {
        String allWords = String.join("", text);
        return Arrays.stream(splitWordsToArray(allWords))
                .map(this::removeNonAlphabeticCharacters)
                .map(word -> word.toLowerCase(Locale.ROOT))
                .toArray(String[]::new);
    }

    private String[] splitWordsToArray(String text) {
        return PATTERN.splitAsStream(text)
                .map(String::trim)
                .toArray(String[]::new);
    }

    private String removeNonAlphabeticCharacters(String word) {
        return ALPHABET_PATTERN.matcher(word).replaceAll("");
    }
}

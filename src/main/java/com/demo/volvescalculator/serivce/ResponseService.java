package com.demo.volvescalculator.serivce;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseService {
    private final WordConventerService wordService;
    private final MapConventerServiceService mapConventerServiceService;

    private final Map<String, List<Float>> response = new HashMap<>();

    public List<String> getProcessedResponse(String words) {
        String[] values = wordService.convertWordsToArray(words);
        buildResponse(values);
        return getCalculatedResults();
    }

    private void buildResponse(String[] words) {
        log.debug("Creating word responses...");
        Arrays.stream(words).forEach(word -> {
            Map<Character, Long> wordVowelMap = mapConventerServiceService.getVowelMap(word);

            Character[] characters = getVowels(wordVowelMap);
            float vowelsCount = (float) countVowels(wordVowelMap);

            StringBuilder builder = constructResponse(word.length(), characters);

            computeValueIfKeyAbsent(vowelsCount, builder);
        });
    }

    private List<String> getCalculatedResults() {
        log.debug("Parsing to response list...");
        return response.entrySet().stream()
                .map(element -> element.getKey() + sumFloats(element.getValue()) / element.getValue().size())
                .map(convertToCurlyBracket())
                .collect(Collectors.toList());
    }

    private Function<String, String> convertToCurlyBracket() {
        return element -> element.replace("[", "{").replace("]", "}");
    }

    private void computeValueIfKeyAbsent(float vowelsCount, StringBuilder builder) {
        response.computeIfAbsent(builder.toString(), k -> new ArrayList<>()).add(vowelsCount);
    }

    private StringBuilder constructResponse(int countCharacters, Character[] characters) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(Arrays.toString(characters));
        builder.append(", ");
        builder.append(countCharacters).append(")").append(" -> ");
        return builder;
    }

    private float sumFloats(List<Float> list) {
        return list.stream().reduce(0F, Float::sum);
    }

    private Character[] getVowels(Map<Character, Long> map) {
        return map.keySet().toArray(new Character[0]);
    }

    private long countVowels(Map<Character, Long> map) {
        return map.values().stream()
                .mapToInt(Long::intValue)
                .summaryStatistics()
                .getSum();
    }
}

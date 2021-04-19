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

    private final ResultService resultService;
    private final WordService wordService;
    private final VowelsCalculatorService vowelsCalculatorService;

    private final Map<String, List<Float>> response = new HashMap<>();

    public List<String> getResponseList(String[] words){
        words = wordService.convertWords(words);
        createResponse(words);
        return getResponses();
    }

    private void createResponse(String[] words) {
        log.debug("Creating word responses...");
        Arrays.stream(words).forEach(word -> {
            Map<Character, Long> wordVowelMap = vowelsCalculatorService.getVowelMap(word);

            Character[] characters = resultService.getVowels(wordVowelMap);
            float vowelsCount = (float) resultService.countVowels(wordVowelMap);

            StringBuilder builder = resultService.constructResponse(word.length(), characters);

            computeValueIfKeyAbsent(vowelsCount, builder);
        });
    }

    private List<String> getResponses() {
        log.debug("Parsing to response list...");
        return response.entrySet().stream()
                .map(element -> element.getKey() + resultService.sumFloats(element.getValue()) / element.getValue().size())
                .map(replaceBracketWithCurlyBracket())
                .collect(Collectors.toList());
    }

    private Function<String, String> replaceBracketWithCurlyBracket() {
        return element -> element.replace("[", "{").replace("]", "}");
    }

    private void computeValueIfKeyAbsent(float vowelsCount, StringBuilder builder) {
        response.computeIfAbsent(builder.toString(), k -> new ArrayList<>()).add(vowelsCount);
    }
}

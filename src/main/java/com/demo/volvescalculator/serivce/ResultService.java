package com.demo.volvescalculator.serivce;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    public Character[] getVowels(Map<Character, Long> map){
        return map.keySet().toArray(new Character[0]);
    }

    public long countVowels(Map<Character, Long> map){
        return map.values().stream()
                .mapToInt(Long::intValue)
                .summaryStatistics()
                .getSum();
    }

    public float sumFloats(List<Float> list) {
        return list.stream().reduce(0F, Float::sum);
    }

    public StringBuilder constructResponse(int countCharacters, Character[] characters) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(Arrays.toString(characters));
        builder.append(", ");
        builder.append(countCharacters).append(")").append(" -> ");
        return builder;
    }

}

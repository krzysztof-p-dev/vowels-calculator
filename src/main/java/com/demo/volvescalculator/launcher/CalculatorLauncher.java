package com.demo.volvescalculator.launcher;

import com.demo.volvescalculator.serivce.ResponseService;
import com.demo.volvescalculator.serivce.WordService;
import com.demo.volvescalculator.utils.FileReader;
import com.demo.volvescalculator.utils.FileWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalculatorLauncher implements CommandLineRunner {

    private static final String INPUT_FILE = "input.txt";

    private final WordService wordService;
    private final ResponseService responseService;
    private final FileWriter fileWriter;

    @Override
    public void run(String... args) {

        log.info("Starting the application... ");

        List<String> linesBuffer = FileReader.readLinesFromFile(INPUT_FILE);

        String[] words = wordService.getWordsArray(linesBuffer.toString());

        List<String> responseList = responseService.getResponseList(words);

        logResponsesToConsole(responseList);

        fileWriter.createNewOutputFile(responseList);

        log.info("Finishing the validation... ");
    }

    private void logResponsesToConsole(List<String> responseList) {
        responseList.forEach(response -> log.info("Response: " + response));
    }
}

package com.demo.volvescalculator.launcher;

import com.demo.volvescalculator.serivce.ResponseService;
import com.demo.volvescalculator.serivce.FileReader;
import com.demo.volvescalculator.serivce.FileWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalculatorLauncher implements CommandLineRunner {

    private final ResponseService responseService;
    private final FileWriter fileWriter;
    private final FileReader fileReader;

    @Override
    public void run(String... args) {

        log.info("Starting the application... ");

        List<String> linesBuffer = fileReader.readLinesFromFile();

        List<String> responseList = responseService.getProcessedResponse(linesBuffer.toString());

        logResponsesToConsole(responseList);

        fileWriter.createNewOutputFile(responseList);

        log.info("Finishing the validation... ");
    }

    private void logResponsesToConsole(List<String> responseList) {
        responseList.forEach(response -> log.info("Response: " + response));
    }
}

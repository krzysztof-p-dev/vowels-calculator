package com.demo.volvescalculator.serivce;

import com.demo.volvescalculator.exception.CalculatorErrorMessage;
import com.demo.volvescalculator.exception.CalculatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public final class FileReader {

    private final String inputFile;

    public List<String> readLinesFromFile() {
        log.info("Loading file: {}", inputFile);

        try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
            return stream.collect(Collectors.toList());
        } catch (IOException ex) {
            throw new CalculatorException(CalculatorErrorMessage.CANNOT_READ_FILE, inputFile);
        }
    }

}

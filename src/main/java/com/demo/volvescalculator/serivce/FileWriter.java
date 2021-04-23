package com.demo.volvescalculator.serivce;

import com.demo.volvescalculator.exception.CalculatorErrorMessage;
import com.demo.volvescalculator.exception.CalculatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileWriter {

    private final String outputDirectory;

    private static final String FILE_NAME = "output.txt";

    public void createNewOutputFile(List<String> lines) {
        deleteFileIfExists();
        writeFile(lines);
    }

    private void writeFile(List<String> lines) {
        try {
            Path filePath = Files.write(Paths.get(outputDirectory, FILE_NAME),
                    lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            log.info("File saved to: " + filePath.toAbsolutePath());
        } catch (IOException ioException) {
            throw new CalculatorException(CalculatorErrorMessage.CANNOT_WRITE_FILE, Paths.get(outputDirectory, FILE_NAME));
        }
    }

    private void deleteFileIfExists() {
        try {
            Files.deleteIfExists(Paths.get(outputDirectory, FILE_NAME));
        } catch (IOException e) {
            throw new CalculatorException(CalculatorErrorMessage.CANNOT_DELETE_FILE, FILE_NAME);
        }
    }

}

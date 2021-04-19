package com.demo.volvescalculator.utils;

import com.demo.volvescalculator.exception.CalculatorErrorMessage;
import com.demo.volvescalculator.exception.CalculatorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileReader {

//    @Value("${path.to.file}")
//    private String fileDirectory;

    public static List<String> readLinesFromFile(String fileName) {
        log.info("Loading file: " + fileName);

        try (Stream<String> stream = Files.lines(getPath(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException ex) {
            throw new CalculatorException(CalculatorErrorMessage.CANNOT_READ_FILE, fileName);
        }
    }

    private static Path getPath(String fileName) {
        String dir = System.getProperty("user.dir");
        log.info("Directory location is: " + dir);
        return Paths.get(dir, fileName);
    }
}

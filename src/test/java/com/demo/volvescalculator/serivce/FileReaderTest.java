package com.demo.volvescalculator.serivce;

import com.demo.volvescalculator.exception.CalculatorException;
import com.demo.volvescalculator.serivce.FileReader;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void shouldThrowCalculatorException() {
        // given
        String path = "nonExitingPath";
        FileReader fileReader = new FileReader(path);

        // when
        CalculatorException calculatorException = assertThrows(CalculatorException.class,
                fileReader::readLinesFromFile);

        // then
        assertEquals(calculatorException.getMessage(), String.format("ERROR: 1 Cannot read file: '%s'", path));
    }

    @Test
    void shouldReadFile() {
        // given
        String path = "src/test/resources/testinput.txt";
        FileReader fileReader = new FileReader(path);
        List<String> expectedResults = ImmutableList.of("Tests are important.", "Always");

        // when
        List<String> readLines = fileReader.readLinesFromFile();

        // then
        assertEquals(expectedResults, readLines);
    }

    @Test
    void readLinesFromFile() {
    }
}
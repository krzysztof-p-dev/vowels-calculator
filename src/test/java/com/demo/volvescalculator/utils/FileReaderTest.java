package com.demo.volvescalculator.utils;

import com.demo.volvescalculator.exception.CalculatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void shouldThrowCalculatorException() {
        // given
        String path = "nonExitingPath";

        // when
        CalculatorException calculatorException = assertThrows(CalculatorException.class,
                () -> FileReader.readLinesFromFile(path));

        // then
        assertEquals(calculatorException.getMessage(), String.format("ERROR: 1 Cannot read file: '%s'", path));
    }

    @Test
    void shouldReadFile() {
        // given
        String path = "src/test/resources/testinput.txt";

        // when

        // then

    }

}
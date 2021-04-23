package com.demo.volvescalculator.serivce;

import com.demo.volvescalculator.exception.CalculatorException;
import com.demo.volvescalculator.serivce.FileWriter;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {

    @Test
    void shouldThrowCalculatorException() {
        // given
        String directory = "input";
        FileWriter fileWriter = new FileWriter(directory);

        // when
        CalculatorException calculatorException = assertThrows(CalculatorException.class,
                () -> fileWriter.createNewOutputFile(ImmutableList.of(directory)));

        // then
        assertEquals(calculatorException.getMessage(), String.format("ERROR: 3 Cannot write file: '%s'",
                String.join("\\", directory, "output.txt")));
    }



}
package com.demo.volvescalculator.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CalculatorErrorMessage {

    CANNOT_READ_FILE(1, "Cannot read file: '%s'"),
    CANNOT_DELETE_FILE(2, "Cannot delete file: '%s'"),
    CANNOT_WRITE_FILE(3, "Cannot write file: '%s'");

    private final int errorNumber;
    private final String errorMessage;
}

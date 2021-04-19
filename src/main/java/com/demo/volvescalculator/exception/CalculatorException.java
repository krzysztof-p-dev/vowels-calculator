package com.demo.volvescalculator.exception;

public class CalculatorException extends RuntimeException {

    private static final String ERROR_MESSAGE_TEMPLATE = "ERROR: %d %s";

    public CalculatorException(CalculatorErrorMessage errorMessage, Object... parameters) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, errorMessage.getErrorNumber(),
                String.format(errorMessage.getErrorMessage(), parameters)));
    }
}

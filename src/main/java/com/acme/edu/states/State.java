package com.acme.edu.states;

import com.acme.edu.Printer;

public abstract class State {

    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String OPEN_BRACKET = "{";
    private static final String CLOSE_BRACKET = "}";
    private static final String CHAR_PREFIX = "char: ";
    private static final String PRIMITIVES_MATRIX_PREFIX = "primitives matrix: ";
    private static final String PRIMITIVES_MULTIMATRIX_PREFIX = "primitives multimatrix: ";
    private static final String REFERENCE_PREFIX = "reference: ";
    private static final String SEP = System.lineSeparator();

    private Printer printer;

    public State(Printer printer) {
        this.printer = printer;
    }

    public void log(char message) {
        displayBuffer();
        printer.print(CHAR_PREFIX + message);
    }

    public void log(boolean message) {
        displayBuffer();
        printer.print(PRIMITIVE_PREFIX + message);
    }

    public void log(int... messages) {
        displayBuffer();
        printer.print(getSumOfNumbersInArray(messages));
    }

    public void log(int[][] matrixMessages) {
        displayBuffer();
        printer.print(PRIMITIVES_MATRIX_PREFIX + OPEN_BRACKET + SEP +
                printMatrix(matrixMessages) + CLOSE_BRACKET);
    }

    public void log(int[][][][] multimatrixMessages) {
        displayBuffer();
        printer.print(PRIMITIVES_MULTIMATRIX_PREFIX + OPEN_BRACKET + SEP +
                printMultimatrix(multimatrixMessages) + CLOSE_BRACKET);
    }

    public void log(String... messages) {
        displayBuffer();
        printer.print(arrayStringToString(messages));
    }

    public void log(Object message) {
        displayBuffer();
        printer.print(REFERENCE_PREFIX + message.toString());
    }

    protected Printer getPrinter() {
        return printer;
    }

    public abstract void displayBuffer();

    public abstract void cleanOrCommutationBuffer(String message);

    private String arrayStringToString(String... messages) {
        StringBuilder result = new StringBuilder();
        for (String message : messages) {
            result.append(message).append(System.lineSeparator());
        }
        return result.toString();
    }

    private String getSumOfNumbersInArray(int[] messages) {
        int sumOfNumbersInArray = 0;
        for (int message : messages) {
            sumOfNumbersInArray += message;
        }
        return String.valueOf(sumOfNumbersInArray);
    }

    private String printMatrix(int[][] arrayMessages) {
        StringBuilder result = new StringBuilder();
        for (int[] arrayMessage : arrayMessages) {
            result.append(OPEN_BRACKET);
            for (int message : arrayMessage) {
                result.append(message).append(", ");
            }
            result.replace(result.length() - 2, result.length(), CLOSE_BRACKET + SEP);
        }
        return result.toString();
    }

    private String printMultimatrix(int[][][][] multimatrixMessages) {
        StringBuilder result = new StringBuilder(OPEN_BRACKET + SEP);
        for (int[][][] multimatrix : multimatrixMessages) {
            result.append(OPEN_BRACKET).append(SEP);
            for (int[][] matrix : multimatrix) {
                result.append(printMatrix(matrix));
            }
            result.append(CLOSE_BRACKET).append(SEP);
        }
        result.append(CLOSE_BRACKET).append(SEP);
        return result.toString();
    }
}

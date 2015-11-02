package com.acme.edu;

public abstract class State {

    protected static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String OPEN_BRACKET = "{";
    private static final String CLOSE_BRACKET = "}";
    private static final String CHAR_PREFIX = "char: ";
    private static final String PRIMITIVES_MATRIX_PREFIX = "primitives matrix: ";
    private static final String PRIMITIVES_MULTIMATRIX_PREFIX = "primitives multimatrix: ";
    private static final String REFERENCE_PREFIX = "reference: ";

    private Printer printer;

    public State(Printer printer) {
        this.printer = printer;
    }

    public void log(int message) {
        magic(String.valueOf(message));
    }

    public void log(String message) {
        magic(message);
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
        printer.print(PRIMITIVES_MATRIX_PREFIX + OPEN_BRACKET);
        printMatrix(matrixMessages);
        printer.print(CLOSE_BRACKET);
    }

    public void log(int[][][][] multimatrixMessages) {
        displayBuffer();
        printer.print(PRIMITIVES_MULTIMATRIX_PREFIX + OPEN_BRACKET);
        printMultimatrix(multimatrixMessages);
        printer.print(CLOSE_BRACKET);
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

    protected abstract void magic(String message);

    protected abstract StateEnum getStateEnum();

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

    private void printMatrix(int[][] arrayMessages) {
        for (int[] arrayMessage : arrayMessages) {
            StringBuilder oneStringArray = new StringBuilder(OPEN_BRACKET);
            for (int message : arrayMessage) {
                oneStringArray.append(message).append(", ");
            }
            oneStringArray.replace(oneStringArray.length() - 2, oneStringArray.length(), CLOSE_BRACKET);
            printer.print(oneStringArray.toString());
        }
    }

    private void printMultimatrix(int[][][][] multimatrixMessages) {
        printer.print(OPEN_BRACKET);
        for (int[][][] multimatrix : multimatrixMessages) {
            printer.print(OPEN_BRACKET);
            for (int[][] matrix : multimatrix) {
                printMatrix(matrix);
            }
            printer.print(CLOSE_BRACKET);
        }
        printer.print(CLOSE_BRACKET);
    }
}

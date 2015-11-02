package com.acme.edu;

/**
 * Logger class provides functions for logging data.
 * For correct processing and the withdrawal of all
 * accumulated data, before the end of the work to
 * the class must call {@link #close()}.
 * <p>
 * Example:
 * <p>
 * <code>public void someMethod() {
 * Logger.log("str 1");
 * Logger.log(1);
 * Logger.log(2);
 * Logger.log("str 2");
 * Logger.log(0);
 * Logger.close();
 * }</code>
 *
 * @autor Tolchinskiy Dmitriy
 */
public class Logger {

    private static final String OPEN_BRACKET = "{";
    private static final String CLOSE_BRACKET = "}";
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String PRIMITIVES_MATRIX_PREFIX = "primitives matrix: ";
    private static final String PRIMITIVES_MULTIMATRIX_PREFIX = "primitives multimatrix: ";
    private static final String REFERENCE_PREFIX = "reference: ";
    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%d)";
    private static final String STRING_PREFIX = "string: ";

    private static int numberBuffer;
    private static String stringBuffer = "";
    private static int count = 1;

    /**
     * Method triggers summing the numbers applied
     * to the input and output in the console.
     *
     * @param message The int to be summing or printed.
     */
    public static void log(int message) {
        printStringBufferAndPrimitiveNumber(message, Integer.MAX_VALUE);
    }

    /**
     * The function of the method is similar to the
     * method {@link #log(int)}.
     *
     * @param message The byte to be summing or printed.
     */
    public static void log(byte message) {
        printStringBufferAndPrimitiveNumber(message, Byte.MAX_VALUE);
    }


    /**
     * Displays character in the console.
     *
     * @param message The char to be printed.
     */
    public static void log(char message) {
        print(CHAR_PREFIX + message);
    }

    /**
     * Displays boolean value in the console.
     *
     * @param message The boolean to be printed.
     */
    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    /**
     * Displays string in the console.
     *
     * @param message The string to be printed.
     */
    public static void log(String message) {
        printNumberBuffer();
        setNewStringBufferAndPrintLastStringBuffer(message);
    }

    /**
     * Displays string representation of integers.
     *
     * @param messages The array integers to be printed.
     */
    public static void log(int... messages) {
        int sumOfNumbersInArray = 0;
        for (int message : messages) {
            sumOfNumbersInArray += message;
        }
        print(String.valueOf(sumOfNumbersInArray));
    }

    /**
     * Displays matrix representation of integers.
     *
     * @param matrixMessages The matrix to be printed.
     */
    public static void log(int[][] matrixMessages) {
        print(PRIMITIVES_MATRIX_PREFIX + OPEN_BRACKET);
        printMatrix(matrixMessages);
        print(CLOSE_BRACKET);
    }

    /**
     * Displays multimatrix representation of integers.
     *
     * @param multimatrixMessages The multimatrix to be printed.
     */
    public static void log(int[][][][] multimatrixMessages) {
        print(PRIMITIVES_MULTIMATRIX_PREFIX + OPEN_BRACKET);
        printMultimatrix(multimatrixMessages);
        print(CLOSE_BRACKET);
    }

    /**
     * It displays the console strings.
     *
     * @param messages The strings to be printed.
     */
    public static void log(String... messages) {
        for (String message : messages) {
            print(message);
        }
    }

    /**
     * Prints an result toString() method Object class in console.
     *
     * @param message The object reference to be printed.
     */
    public static void log(Object message) {
        print(REFERENCE_PREFIX + message.toString());
    }


    /**
     * Displays the residual data to the console.
     * Called before the cessation of work with logger.
     */
    public static void close() {
        printNumberBuffer();
        printStringBuffer();
    }

    private static void printMultimatrix(int[][][][] multimatrixMessages) {
        print(OPEN_BRACKET);
        for (int[][][] multimatrix : multimatrixMessages) {
            print(OPEN_BRACKET);
            for (int[][] matrix : multimatrix) {
                printMatrix(matrix);
            }
            print(CLOSE_BRACKET);
        }
        print(CLOSE_BRACKET);
    }

    private static void printMatrix(int[][] arrayMessages) {
        for (int[] arrayMessage : arrayMessages) {
            StringBuilder oneStringArray = new StringBuilder(OPEN_BRACKET);
            for (int message : arrayMessage) {
                oneStringArray.append(message).append(", ");
            }
            oneStringArray.replace(oneStringArray.length() - 2, oneStringArray.length(), CLOSE_BRACKET);
            print(oneStringArray.toString());
        }
    }

    private static void printStringBufferAndPrimitiveNumber(int message, int maxValue) {
        printStringBuffer();
        printPrimitiveNumber(message, maxValue);
    }

    private static void setNewStringBufferAndPrintLastStringBuffer(String message) {
        if (stringBuffer.equals(message)) {
            count++;
        } else {
            printStringBuffer();
            stringBuffer = message;
        }
    }

    private static void printPrimitiveNumber(int message, int maxValue) {
        if (message != 0 && message < maxValue) {
            summing(message);
        } else if (message == maxValue) {
            print(PRIMITIVE_PREFIX + numberBuffer);
            print(PRIMITIVE_PREFIX + maxValue);
            numberBuffer = 0;
        } else if (numberBuffer == 0) {
            print(PRIMITIVE_PREFIX + 0);
        } else {
            print(PRIMITIVE_PREFIX + message);
        }
    }

    private static void summing(int message) {
        int temp = numberBuffer;
        numberBuffer += message;
        if (numberBuffer < temp && message > 0) {
            print(PRIMITIVE_PREFIX + temp);
            numberBuffer = message;
        }
    }

    private static void printStringBuffer() {
        if (stringBuffer.isEmpty()) {
            return;
        }

        if (count > 1) {
            print(String.format(STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX, stringBuffer, count));
            count = 1;
        } else {
            print(STRING_PREFIX + stringBuffer);
        }
        stringBuffer = "";

    }

    private static void printNumberBuffer() {
        if (numberBuffer == 0) {
            return;
        }
        print(PRIMITIVE_PREFIX + numberBuffer);
        numberBuffer = 0;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}

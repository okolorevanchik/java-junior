package com.acme.edu;

import java.util.Arrays;

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

    private static final int ZERO_VALUE = 0;
    private static final int ONE_VALUE = 1;

    private static int sum;
    private static String lastString = "";
    private static int count = 1;

    /**
     * Method triggers summing the numbers applied
     * to the input and output in the console.
     *
     * @param message The int to be summing or printed.
     */
    public static void log(int message) {
        printLastStringAndPrimitiveNumber(message, Integer.MAX_VALUE);
    }

    /**
     * The function of the method is similar to the
     * method {@link #log(int)}.
     *
     * @param message The byte to be summing or printed.
     */
    public static void log(byte message) {
        printLastStringAndPrimitiveNumber(message, Byte.MAX_VALUE);
    }


    /**
     * Displays character in the console.
     *
     * @param message The char to be printed.
     */
    public static void log(char message) {
        print("char: " + message);
    }

    /**
     * Displays boolean value in the console.
     *
     * @param message The boolean to be printed.
     */
    public static void log(boolean message) {
        print("primitive: " + message);
    }

    /**
     * Displays string in the console.
     *
     * @param message The string to be printed.
     */
    public static void log(String message) {
        printSum();
        setNewLastStringAndPrintLastString(message);
    }

    /**
     * Displays string representation of integers.
     *
     * @param messages The array integers to be printed.
     */
    public static void log(int... messages) {
        int sumOfNumbersInArray = ZERO_VALUE;
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
        print("primitives matrix: {");
        printMatrix(matrixMessages);
        print("}");
    }

    /**
     * Displays multimatrix representation of integers.
     *
     * @param multimatrixMessages The multimatrix to be printed.
     */
    public static void log(int[][][][] multimatrixMessages) {
        print("primitives multimatrix: {");
        printMultimatrix(multimatrixMessages);
        print("}");
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
        print("reference: " + message.toString());
    }


    /**
     * Displays the residual data to the console.
     * Called before the cessation of work with logger.
     */
    public static void close() {
        printSum();
        printLastString();
    }

    private static void printMultimatrix(int[][][][] multimatrixMessages) {
        for (int[][][] multimatrix : multimatrixMessages) {
            print("{");
            for (int[][] matrix : multimatrix) {
                printInnerMatrix(matrix);
            }
            print("}");
        }
    }

    private static void printInnerMatrix(int[][] matrix) {
        print("{");
        for (int[] array : matrix) {
            print("{");
            for (int number : array) {
                print(String.valueOf(number));
            }
            print("}");
        }
        print("}");
    }

    private static void printMatrix(int[][] arrayMessages) {
        for (int[] arrayMessage : arrayMessages) {
            String arrayStringMessage = Arrays.toString(arrayMessage)
                    .replace("[", "{")
                    .replace("]", "}");
            print(arrayStringMessage);
        }
    }

    private static void printLastStringAndPrimitiveNumber(int message, int maxValue) {
        printLastString();
        printPrimitiveNumber(message, maxValue);
    }

    private static void setNewLastStringAndPrintLastString(String message) {
        if (lastString.equals(message)) {
            count++;
        } else {
            printLastString();
            lastString = message;
        }
    }

    private static void printPrimitiveNumber(int message, int maxValue) {
        if (message != ZERO_VALUE && message < maxValue) {
            summing(message);
        } else if (message == maxValue) {
            print("primitive: " + sum);
            print("primitive: " + maxValue);
            sum = ZERO_VALUE;
        } else if (sum == ZERO_VALUE) {
            print("primitive: " + ZERO_VALUE);
        } else {
            print("primitive: " + message);
        }
    }

    private static void summing(int message) {
        int temp = sum;
        sum += message;
        if (sum < temp && message > ZERO_VALUE) {
            print("primitive: " + temp);
            sum = message;
        }
    }

    private static void printLastString() {
        if (lastString.isEmpty()) {
            return;
        }

        if (count > ONE_VALUE) {
            print(String.format("string: %s (x%d)", lastString, count));
            count = ONE_VALUE;
        } else {
            print("string: " + lastString);
        }
        lastString = "";

    }

    private static void printSum() {
        if (sum == ZERO_VALUE) {
            return;
        }
        print("primitive: " + sum);
        sum = ZERO_VALUE;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}

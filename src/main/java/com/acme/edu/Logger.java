package com.acme.edu;

import java.util.Arrays;

/**
 * Class Logger implemented logging function.
 *
 * @autor Tolchinskiy Dmitriy
 */
public class Logger {

    private static final int ZERO_VALUE = 0;

    private static int sum;
    private static String lastString = "";
    private static int count = 1;

    /**
     * Prints an integer number and then terminate the line.
     *
     * @param message The int to be printed.
     */
    public static void log(int message) {
        printLastStringAndPrimitiveNumber(message, Integer.MAX_VALUE);
    }

    /**
     * Prints an byte number and then terminate the line.
     *
     * @param message The int to be printed.
     */
    public static void log(byte message) {
        printLastStringAndPrimitiveNumber(message, Byte.MAX_VALUE);
    }


    /**
     * Prints an character and then terminate the line.
     *
     * @param message The char to be printed.
     */
    public static void log(char message) {
        close();
        print("char: " + message);
    }

    /**
     * Prints an boolean and then terminate the line.
     *
     * @param message The boolean to be printed.
     */
    public static void log(boolean message) {
        close();
        print("primitive: " + message);
    }

    /**
     * Prints an string and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(String message) {
        printSum();
        setNewLastStringAndPrintLastString(message);
    }

    /**
     * Returns a string representation of the integer.
     *
     * @param messages The array to be  printed.
     */
    public static void log(int[] messages) {
        close();
        print("primitives array: " + getArrayStringMessage(messages));
    }

    /**
     * Later...
     *
     * @param matrixMessages The matrix to be printed.
     */
    public static void log(int[][] matrixMessages) {
        close();
        print("primitives matrix: {");
        printMax(matrixMessages);
        print("}");
    }

    /**
     * Later...
     *
     * @param multimatrixMessages The multimatrix to be printed.
     */
    public static void log(int[][][][] multimatrixMessages) {
        close();
        print("primitives multimatrix: {");
        for (int[][][] multimatrix : multimatrixMessages) {
            print("{");
            for (int[][] matrix : multimatrix) {
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
            print("}");
        }
        print("}");
    }

    /**
     * Prints an result toString() method Object class and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(Object message) {
        close();
        print("reference: " + message.toString());
    }


    /**
     * Later...
     */
    public static void close() {
        printSum();
        printLastString();
    }

    private static void printMax(int[][] arrayMessages) {
        for (int[] arrayMessage : arrayMessages) {
            print(getArrayStringMessage(arrayMessage));
        }
    }

    private static String getArrayStringMessage(int[] messages) {
        return Arrays.toString(messages).replace("[", "{").replace("]", "}");
    }

    private static void printLastStringAndPrimitiveNumber(int message, int maxValue) {
        printLastString();
        printPrimitiveNumber(message, maxValue);
    }

    private static void setNewLastStringAndPrintLastString(String message) {
        if (lastString.isEmpty()) {
            lastString = message;
        } else if (lastString.equals(message)) {
            count++;
        } else {
            printLastString();
            lastString = message;
        }
    }

    private static void printPrimitiveNumber(int message, int maxValue) {
        if (message != ZERO_VALUE && message < maxValue) {
            sum += message;
        } else if (message == maxValue) {
            print("primitive: " + sum);
            print("primitive: " + maxValue);
            sum = 0;
        } else {
            print("primitive: " + message);
        }
    }

    private static void printLastString() {
        if (lastString.isEmpty()) {
            return;
        }

        if (count > 1) {
            print(String.format("string: %s (x%d)", lastString, count));
            count = 1;
        } else {
            print("string: " + lastString);
        }
        lastString = "";

    }

    private static void printSum() {
        if (sum == 0) {
            return;
        }
        print("primitive: " + sum);
        sum = 0;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}

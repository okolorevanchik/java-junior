package com.acme.edu;

/**
 * Class Logger implemented logging function.
 *
 * @autor Tolchinskiy Dmitriy
 */
public class Logger {

    private static int SUM;

    /**
     * Prints an integer number and then terminate the line.
     *
     * @param message The int to be printed.
     */
    public static void log(int message) {
        if (message != 0 && message != Integer.MAX_VALUE) SUM += message;
        else if (message == Integer.MAX_VALUE) {
            print("primitive: " + SUM);
            SUM = 0;
            print("primitive: " + Integer.MAX_VALUE);
        } else {
            print("primitive: " + message);
        }
    }

    public static void log(byte message) {
        if (message != 0 && message != Byte.MAX_VALUE) SUM += message;
        else if (message == Byte.MAX_VALUE) {
            print("primitive: " + SUM);
            SUM = 0;
            print("primitive: " + Byte.MAX_VALUE);
        } else {
            print("primitive: " + message);
        }
    }

    /**
     * Prints an character and then terminate the line.
     *
     * @param message The char to be printed.
     */
    public static void log(char message) {
        isSumNull();
        print("char: " + message);
    }

    public static void log(boolean message) {
        isSumNull();
        print("primitive: " + message);
    }

    /**
     * Prints an string and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(String message) {
        isSumNull();
        if (message.contains("str ")) print(message);
        else print("string: " + message);
    }

    /**
     * Prints an result toString() method Object class and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(Object message) {
        isSumNull();
        print("reference: " + message.toString());
    }

    private static void isSumNull() {
        if (SUM != 0) {
            print("primitive: " + SUM);
            SUM = 0;
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

}

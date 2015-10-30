package com.acme.edu;

/**
 * Class Logger implemented logging function.
 *
 * @autor Tolchinskiy Dmitriy
 */
public class Logger {

    /**
     * Prints an integer number and then terminate the line.
     *
     * @param message The int to be printed.
     */
    public static void log(int message) {
        print("primitive: " + message);
    }

    /**
     * Prints an character and then terminate the line.
     *
     * @param message The char to be printed.
     */
    public static void log(char message) {
        print("char: " + message);
    }

    /**
     * Prints an string and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(String message) {
        print("string: " + message);
    }

    /**
     * Prints an result toString() method Object class and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(Object message) {
        print("reference: " + message.toString());
    }

    private static void print(String message) {
        System.out.println(message);
    }

}

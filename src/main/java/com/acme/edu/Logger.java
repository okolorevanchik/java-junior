package com.acme.edu;

public class Logger {

    private static final String PRIMITIVE = "primitive: ";

    /**
     * Prints an integer and then terminate the line.
     * @param message The int to be printed.
     */
    public static void log(int message) {
        consoleWriter(PRIMITIVE + message);
    }

    /**
     * Prints an boolean and then terminate the line.
     * @param message The boolean to be printed.
     */
    public static void log(boolean message) {
        consoleWriter(PRIMITIVE + message);
    }

    /**
     * Prints an character and then terminate the line.
     * @param message The char to be printed.
     */
    public static void log(char message) {
        consoleWriter("char: " + message);
    }

    private static void consoleWriter(String str) {
        System.out.println(str);
    }
}

package com.acme.edu;

public class Logger {

    public static void log(int message) {
        consoleWriter("primitive: " + message);
    }

    public static void log(byte message) {
        consoleWriter("primitive: " + message);
    }

    public static void log(boolean message) {
        consoleWriter("primitive: " + message);
    }

    public static void log(char message) {
        consoleWriter("char: " + message);
    }

    private static void consoleWriter(String str) {
        System.out.println(str);
    }
}

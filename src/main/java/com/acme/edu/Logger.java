package com.acme.edu;

/**
 * Class Logger implemented logging function.
 *
 * @autor Tolchinskiy Dmitriy
 */
public class Logger {

    private static int sum;
    private static String lastString = "";
    private static int count = 1;

    /**
     * Prints an integer number and then terminate the line.
     *
     * @param message The int to be printed.
     */
    public static void log(int message) {
        printLastString();
        lastString = "";

        if (message != 0 && message != Integer.MAX_VALUE)
            sum += message;
        else if (message == Integer.MAX_VALUE) {
            print("primitive: " + sum);
            sum = 0;
            print("primitive: " + Integer.MAX_VALUE);
        } else print("primitive: " + message);

    }


    public static void log(byte message) {
        printLastString();
        lastString = "";

        if (message != 0 && message != Byte.MAX_VALUE)
            sum += message;
        else if (message == Byte.MAX_VALUE) {
            print("primitive: " + sum);
            sum = 0;
            print("primitive: " + Byte.MAX_VALUE);
        } else print("primitive: " + message);

    }

    /**
     * Prints an character and then terminate the line.
     *
     * @param message The char to be printed.
     */
    public static void log(char message) {
        printSum();
        print("char: " + message);
    }

    public static void log(boolean message) {
        printSum();
        print("primitive: " + message);
    }

    /**
     * Prints an string and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(String message) {
        printSum();

        if (lastString.isEmpty()) lastString = message;
        else if (lastString.equals(message)) count++;
        else {
            printLastString();
            lastString = message;
        }
    }

    /**
     * Prints an result toString() method Object class and then terminate the line.
     *
     * @param message The string to be printed.
     */
    public static void log(Object message) {
        printSum();
        print("reference: " + message.toString());
    }

    public static void close() {
        if (!lastString.isEmpty()) {
            printLastString();
            lastString = "";
        }
    }

    private static void printLastString() {
        if (!lastString.isEmpty()) {
            if (count > 1)
                print("string: " + lastString + " (x" + count + ")");
            else print("string: " + lastString);

            count = 1;
        }
    }

    private static void printSum() {
        if (sum != 0) {
            print("primitive: " + sum);
            sum = 0;
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

}

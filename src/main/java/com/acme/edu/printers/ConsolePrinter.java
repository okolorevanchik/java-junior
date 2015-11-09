package com.acme.edu.printers;

/**
 * @author Tolchinskiy Dmitriy
 */
public class ConsolePrinter implements Printable {

    /**
     * @param message
     * @param flush
     */
    @Override
    public void print(String message, boolean flush) {
        System.out.println(message);
    }
}

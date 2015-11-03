package com.acme.edu;

/**
 * @author Tolchinskiy Dmitriy
 */
public class ConsolePrinter implements Printable {

    /**
     * @param message
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}

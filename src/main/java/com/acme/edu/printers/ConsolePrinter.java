package com.acme.edu.printers;

/**
 * Class ConsolePrinter implements Printer
 */
public class ConsolePrinter implements Printable {

    /**
     * Print to console
     *
     * @param message Report output
     * @param flush   Not used
     */
    @Override
    public void print(String message, boolean flush) {
        System.out.println(message);
    }
}

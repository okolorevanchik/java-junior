package com.acme.edu;

public class Decorator {

    private Printable printable;

    public Decorator(Printable printable) {
        this.printable = printable;
    }

    private void decorateAndPrint(String format, String... args) {
        String result = String.format(format, args);
        printable.print(result);
    }
}

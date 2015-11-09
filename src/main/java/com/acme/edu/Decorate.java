package com.acme.edu;

/**
 * Interface decorating string.
 */
@FunctionalInterface
public interface Decorate {
    /**
     * It allows you to implement your logic decorating line.
     *
     * @param format Format decoration
     * @param args   Arguments decoration
     * @return Decorated for the specified format arguments
     */
    String getDecorateString(String format, String... args);
}

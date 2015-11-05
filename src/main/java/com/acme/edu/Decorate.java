package com.acme.edu;

@FunctionalInterface
public interface Decorate {
    String getDecorateString(String format, String... args);
}

package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataException;

public interface Printable {
    void print(String message, boolean flush) throws PrintDataException;
}

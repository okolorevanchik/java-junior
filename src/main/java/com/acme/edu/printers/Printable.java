package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataException;

/**
 * The interface that abstracts output.
 */
public interface Printable {
    /**
     * It serves to output data
     *
     * @param message Report output
     * @param flush   Flag forced write data from the buffer.
     * @throws PrintDataException
     */
    void print(String message, boolean flush) throws PrintDataException;
}

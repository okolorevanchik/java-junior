package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataToFileException;

/**
 * FilePrinter implements Printable
 * Print to file.
 */
public class FilePrinter extends AbstractFilePrinter {

    /**
     * Initializes an object for writing to a file.
     *
     * @param coding        The encoding of the output file.
     * @param pathToLogFile The path to the output file.
     */
    public FilePrinter(String coding, String pathToLogFile) throws PrintDataToFileException {
        super(coding, pathToLogFile);
    }

    @Override
    protected void flushesFile() throws PrintDataToFileException {
        writeDataToFile();
        getBuffer().clear();
    }
}

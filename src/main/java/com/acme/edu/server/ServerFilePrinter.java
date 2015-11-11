package com.acme.edu.server;

import com.acme.edu.exceptions.PrintDataToFileException;
import com.acme.edu.printers.AbstractFilePrinter;

/**
 * ServerFilePrinter implements Printable
 * Print to file on server.
 */
public class ServerFilePrinter extends AbstractFilePrinter {

    private static final String SUBSTRING_FOR_SORTING = "ERROR";

    /**
     * Initializes an object for writing to a file on server.
     *
     * @param coding        The encoding of the output file.
     * @param pathToLogFile The path to the output file.
     */
    public ServerFilePrinter(String coding, String pathToLogFile) throws PrintDataToFileException {
        super(coding, pathToLogFile);
    }

    @Override
    protected void flushesFile() throws PrintDataToFileException {
        sortList();
        writeDataToFile();
        getBuffer().clear();
    }

    private void sortList() {
        getBuffer().sort((o1, o2) -> {
            if (o1.contains(SUBSTRING_FOR_SORTING) && o2.contains(SUBSTRING_FOR_SORTING))
                return 0;
            else if (o1.contains(SUBSTRING_FOR_SORTING) && !o2.contains(SUBSTRING_FOR_SORTING))
                return 1;
            else return -1;
        });
    }


}

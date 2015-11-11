package com.acme.edu.server;

import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.exceptions.PrintDataToFileException;
import com.acme.edu.printers.Printable;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ServerFilePrinter implements Printable
 * Print to file on server.
 */
public class ServerFilePrinter implements Printable {

    private static final String SUBSTRING_FOR_SORTING = "ERROR";

    private String pathToLogFile;
    private String coding;
    private final List<String> buffer = new ArrayList<>();

    /**
     * Initializes an object for writing to a file on server.
     *
     * @param coding        The encoding of the output file.
     * @param pathToLogFile The path to the output file.
     */
    public ServerFilePrinter(String coding, String pathToLogFile) {
        this.coding = coding;
        this.pathToLogFile = pathToLogFile;
    }

    /**
     * Print to file messages when buffer.size() == 50 or flush is true
     * It creates an output file if it is not in the specified path.
     * Sort buffer before output to a file by keyword ERROR.
     *
     * @param message String
     * @param flush   Flag forced write data from the buffer.
     * @throws PrintDataToFileException
     */
    @Override
    public void print(String message, boolean flush) throws PrintDataException {
        synchronized (buffer) {
            Path path = Paths.get(pathToLogFile);
            checkExistsFile(path);

            buffer.add(message);
            if (flush || buffer.size() == 50) {
                sortList();
                writeDataToFile(path);
                buffer.clear();
            }
        }
    }

    private void sortList() {
        buffer.sort((o1, o2) -> {
            if (o1.contains(SUBSTRING_FOR_SORTING) && o2.contains(SUBSTRING_FOR_SORTING))
                return 0;
            else if (o1.contains(SUBSTRING_FOR_SORTING) && !o2.contains(SUBSTRING_FOR_SORTING))
                return 1;
            else return -1;
        });
    }

    private void writeDataToFile(Path path) throws PrintDataToFileException {
        try (OutputStream outputStream = new FileOutputStream(path.toFile(), true);
             Writer writer = new OutputStreamWriter(outputStream, Charset.forName(coding));
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (String s : buffer) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new PrintDataToFileException(e);
        }
    }

    private void checkExistsFile(Path path) throws PrintDataToFileException {
        if (!Files.isRegularFile(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new PrintDataToFileException(e);
            }
        }
    }
}

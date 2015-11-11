package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.exceptions.PrintDataToFileException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilePrinter implements Printable {

    private final String coding;
    private final Path path;
    private final List<String> buffer = new ArrayList<>();

    protected AbstractFilePrinter(String coding, String pathToLogFile) throws PrintDataToFileException {
        this.coding = coding;
        this.path = checkExistsFile(pathToLogFile);
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
            buffer.add(message);
            if (flush || getBuffer().size() == 50) {
                flushesFile();
            }
        }
    }

    protected List<String> getBuffer() {
        return buffer;
    }

    protected void writeDataToFile() throws PrintDataToFileException {
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

    protected abstract void flushesFile() throws PrintDataToFileException;

    private Path checkExistsFile(String pathToLogFile) throws PrintDataToFileException {
        Path path = Paths.get(pathToLogFile);
        if (!Files.isRegularFile(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new PrintDataToFileException(e);
            }
        }
        return path;
    }

}

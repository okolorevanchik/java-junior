package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataToFileException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePrinter implements Printable {

    private String pathToLogFile;
    private String coding;
    private List<String> buffer;

    public FilePrinter(String coding, String pathToLogFile) {
        this.coding = coding;
        this.pathToLogFile = pathToLogFile;
        this.buffer = new ArrayList<>();
    }

    @Override
    public void print(String message, boolean flush) throws PrintDataToFileException {
        Path path = Paths.get(pathToLogFile);
        checkExistsFile(path);

        buffer.add(message);
        if (flush || buffer.size() == 50) {
            writeDataToFile(path);
            buffer.clear();
        }
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

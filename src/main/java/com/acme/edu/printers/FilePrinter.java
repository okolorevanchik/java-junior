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

    private static final String PATH_TO_FILE = "OutLog.txt";
    private List<String> buffer = new ArrayList<>();
    private String coding;

    public FilePrinter(String coding) {
        this.coding = coding;
    }

    @Override
    public void print(String message) throws PrintDataToFileException {
        Path path = Paths.get(PATH_TO_FILE);
        checkExistsFile(path);
        if (isFullBuffer(message)) {
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
            throw new PrintDataToFileException("There was an error writing to file.");
        }
    }

    private boolean isFullBuffer(String message) {
        buffer.add(message);
        return buffer.size() == 50;
    }

    private void checkExistsFile(Path path) throws PrintDataToFileException {
        if (!Files.isRegularFile(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new PrintDataToFileException("There was an error creating file.");
            }
        }
    }
}

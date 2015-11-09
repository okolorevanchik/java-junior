package com.acme.edu;

import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.Printable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class LoggerServer {

    private static final String CODING = "UTF-8";
    private static final String PATH_TO_LOG_FILE = "ServerLog.txt";

    private int port;
    private Printable printable;

    public LoggerServer(int port) {
        this.port = port;
        this.printable = new FilePrinter(CODING, PATH_TO_LOG_FILE);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000);
            while (true) {
                try (Socket client = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
                    readingRequestFromClient(reader, writer);
                } catch (SocketTimeoutException ignored) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readingRequestFromClient(BufferedReader reader, BufferedWriter writer) throws IOException {
        try {
            String result;
            while ((result = reader.readLine()) != null) {
                printable.print(result, false);
            }
            writer.write("WRITE");
        } catch (PrintDataException e) {
            writer.write("ERROR");
            writer.newLine();
            writer.write(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LoggerServer loggerServer = new LoggerServer(10000);
        loggerServer.start();
    }
}

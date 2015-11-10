package com.acme.edu.server;

import com.acme.edu.exceptions.LoggerServerException;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.Printable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Simple server logging messages coming from clients.
 */
public class LoggerServer {

    private static final String PATH_TO_LOG_FILE = "ServerLog.txt";
    private static final Executor THREAD_POOL = Executors.newWorkStealingPool();

    private int port;
    private String coding;
    private Printable printable;

    /**
     * Initializes the server.
     *
     * @param port   Is the port that the server will listen.
     * @param coding Encoding data storage
     */
    public LoggerServer(int port, String coding) {
        this.port = port;
        this.coding = coding;
        this.printable = new FilePrinter(coding, PATH_TO_LOG_FILE);
    }

    public static void main(String[] args) throws LoggerServerException {
        LoggerServer loggerServer = new LoggerServer(11111, "UTF-8");
        loggerServer.start();
    }

    /**
     * It starts the server.
     */
    public void start() throws LoggerServerException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(20000);
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    THREAD_POOL.execute(() -> {
                        try(BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), coding));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), coding))) {
                            readingRequestFromClient(reader, writer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (SocketTimeoutException e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new LoggerServerException(e);
        }
    }

    private void readingRequestFromClient(BufferedReader reader, BufferedWriter writer) throws IOException {
        try {
            String send = getResponse(reader);
            String message = getResponse(reader);
            printable.print(message, "FLUSH".equals(send));
            sendResponse(writer, "OK");
        } catch (PrintDataException e) {
            sendResponse(writer, "ERROR");
            sendResponse(writer, e.getMessage());
        }
    }

    private void sendResponse(BufferedWriter writer, String ok) throws IOException {
        writer.write(ok);
        writer.newLine();
        writer.flush();
    }

    private String getResponse(BufferedReader reader) throws IOException {
        while (true) {
            String result;
            if (reader.ready() && (result = reader.readLine()) != null) {
                return result;
            }
        }
    }
}

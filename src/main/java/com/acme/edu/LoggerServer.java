package com.acme.edu;

import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.Printable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Simple server logging messages coming from clients.
 */
public class LoggerServer {

    private static final String CODING = "UTF-8";
    private static final String PATH_TO_LOG_FILE = "ServerLog.txt";

    private int port;
    private Printable printable;

    /**
     * Initializes the server.
     *
     * @param port Is the port that the server will listen.
     */
    public LoggerServer(int port) {
        this.port = port;
        this.printable = new FilePrinter(CODING, PATH_TO_LOG_FILE);
    }

    public static void main(String[] args) throws InterruptedException {
        LoggerServer loggerServer = new LoggerServer(11111);
        loggerServer.start();
    }

    /**
     * It starts the server.
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000);
            while (true) {
                try (Socket client = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream())) {
                    readingRequestFromClient(ois, oos);
                } catch (SocketTimeoutException ignored) {
                    System.out.println("Server is running.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readingRequestFromClient(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        try {
            String send = ois.readUTF();
            List<String> result = (List<String>) ois.readObject();
            for (String message : result) {
                printable.print(message, send.equals("FLUSH"));
            }
            oos.writeUTF("OK");
        } catch (PrintDataException | ClassNotFoundException e) {
            oos.writeUTF("ERROR");
            oos.writeObject(e);
        }
    }
}

package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataToNetworkException;

import java.io.*;
import java.net.Socket;

/**
 * RemotePrinter implements Printable
 * Pass to LoggerServer messages
 */
public class RemotePrinter implements Printable {

    private String address;
    private int port;
    private String coding;

    /**
     * Initializes an object to send messages to a remote server.
     *
     * @param address Server address
     * @param port    Server port
     * @param coding  Message Encoding
     */
    public RemotePrinter(String address, int port, String coding) {
        this.address = address;
        this.port = port;
        this.coding = coding;
    }

    /**
     * Print to server messages when buffer.size() == 50 or flush
     *
     * @param message String
     * @param flush   Flag forced write data from the buffer.
     * @throws PrintDataToNetworkException
     */
    @Override
    public void print(String message, boolean flush) throws PrintDataToNetworkException {
        sendDataToServer(message, flush ? "FLUSH" : "WRITE");
    }

    private void sendDataToServer(String message, String send) throws PrintDataToNetworkException {
        try (Socket socket = new Socket(address, port);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), coding));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), coding))) {

            sendMessage(send, writer);
            sendMessage(message, writer);
            readResponseFromServer(reader);
        } catch (IOException e) {
            throw new PrintDataToNetworkException(e);
        }
    }

    private void sendMessage(String send, BufferedWriter writer) throws IOException {
        writer.write(send);
        writer.newLine();
        writer.flush();
    }

    private void readResponseFromServer(BufferedReader reader) throws IOException, PrintDataToNetworkException {
        while (true) {
            if (reader.ready()) {
                if (reader.readLine().equals("ERROR")) {
                    throw new PrintDataToNetworkException(reader.readLine());
                } else break;
            }
        }
    }
}

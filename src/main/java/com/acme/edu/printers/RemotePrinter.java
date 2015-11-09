package com.acme.edu.printers;

import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.exceptions.PrintDataToNetworkException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RemotePrinter implements Printable {

    private String address;
    private int port;
    private List<String> buffer;

    public RemotePrinter(String address, int port) {
        this.address = address;
        this.port = port;
        this.buffer = new ArrayList<>();
    }

    @Override
    public void print(String message, boolean flush) throws PrintDataException {
        buffer.add(message);
        if (flush || buffer.size() == 50) {
            sendDataToServer(flush ? "FLUSH" : "WRITE");
            buffer.clear();
        }
    }

    private void sendDataToServer(String send) throws PrintDataToNetworkException {
        try (Socket socket = new Socket(address, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            oos.writeUTF(send);
            oos.writeObject(buffer);
            if (ois.readUTF().equals("ERROR")) {
                Exception e = (Exception) ois.readObject();
                throw new PrintDataToNetworkException(e);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new PrintDataToNetworkException(e);
        }
    }
}

package com.acme.edu.server;

import com.acme.edu.exceptions.LoggerServerException;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.exceptions.PrintDataToFileException;
import com.acme.edu.printers.Printable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simple server logging messages coming from clients.
 */
public class LoggerServer {

    private static final String PATH_TO_LOG_FILE = "ServerLogTeam13.txt";
    private static final ExecutorService THREAD_POOL = Executors.newWorkStealingPool();

    private int port;
    private String coding;
    private Printable printable;
    private Thread server;
    private Collection<Socket> openSockets = new LinkedList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * Initializes the server.
     * Creating object
     *
     * @param port   Is the port that the server will listen.
     * @param coding Encoding data storage
     */
    public LoggerServer(int port, String coding) throws LoggerServerException {
        try {
            this.port = port;
            this.coding = coding;
            this.printable = new ServerFilePrinter(coding, PATH_TO_LOG_FILE);
        } catch (PrintDataToFileException e) {
            throw new LoggerServerException(e);
        }
    }

    public static void main(String[] args) throws LoggerServerException, InterruptedException {
        LoggerServer loggerServer = new LoggerServer(11111, "UTF-8");
        loggerServer.start();
        Thread.sleep(10000);
        loggerServer.stop();
    }

    /**
     * It starts the server.
     */
    public void start() throws LoggerServerException {
        server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.setSoTimeout(1000);
                jobServer(serverSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start();
    }

    /**
     * Stops the server.
     */
    public void stop() {
        server.interrupt();
    }

    private void jobServer(ServerSocket serverSocket) throws IOException {
        while (true) {
            try {
                startWorkWithClient(serverSocket);
            } catch (SocketTimeoutException e) {
                if (isStop()) {
                    break;
                }
            }
        }
    }

    private void startWorkWithClient(ServerSocket serverSocket) throws IOException {
        Socket client = serverSocket.accept();
        lock.writeLock().lock();
        openSockets.add(client);
        lock.writeLock().unlock();
        THREAD_POOL.execute(() -> workWithClient(client));
    }

    private void workWithClient(Socket client) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), coding));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), coding))) {
            readingRequestFromClient(reader, writer);
        } catch (IOException e) {
            lock.writeLock().lock();
            openSockets.remove(client);
            lock.writeLock().lock();
        }
    }

    private boolean isStop() throws IOException {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            closeSockets();
            THREAD_POOL.shutdown();
            return true;
        }
        return false;
    }

    private void closeSockets() throws IOException {
        lock.readLock().lock();
        for (Socket socket : openSockets) {
            if (!socket.isClosed()) {
                socket.close();
            }
        }
        lock.writeLock().unlock();
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

package pl.edu.pjwst.clientServerApp.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int SERVER_PORT = 5000;

    public static void log(String message) {
        System.out.printf("[S] %s" + System.lineSeparator(), message);
    }

    public static void main(String[] args) throws IOException {
        log("Server starting");
        log("Opening server socket");
        ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
        log("Server socket opened");

        log("Server listening");
        Socket clientSocket = welcomeSocket.accept();
        String clientIp = clientSocket.getInetAddress().toString();
        int clientPort = clientSocket.getPort();
        log(String.format("Client connected from %s:%d" + System.lineSeparator(), clientIp, clientPort));



        log("Closing client socket");
        clientSocket.close();
        log("Client socket closed");

        log("Closing server socket");
        welcomeSocket.close();
        log("Server socket closed");
        log("Server ends");

    }

}

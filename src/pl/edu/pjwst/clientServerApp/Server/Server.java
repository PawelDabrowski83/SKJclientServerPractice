package pl.edu.pjwst.clientServerApp.Server;

import java.io.*;
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

        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        log("Stream collected");

        String request = br.readLine();
        log("Request " + request);

        if("LOGIN".equals(request)) {
            bw.write("ACK");
            bw.newLine();
            bw.flush();
        }

        do {
            request = br.readLine();
            log("Req: " + request);
        } while (!"QUIT".equals(request));


        log("Closing client socket");
        clientSocket.close();
        log("Client socket closed");

        log("Closing server socket");
        welcomeSocket.close();
        log("Server socket closed");
        log("Server ends");

    }

}

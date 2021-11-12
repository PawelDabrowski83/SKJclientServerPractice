package pl.edu.pjwst.clientServerApp.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void log(String message) {
        System.out.printf("[C]: %s" + System.lineSeparator(), message);
    }

    public static void main(String[] args) throws IOException {
        log("Starting");
        InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
        log(String.format("Server address: %s" + System.lineSeparator(), serverAddress.toString()));
        log("Connecting to server " + serverAddress.toString());
        Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
        log("Connected to server " + serverAddress.toString());

        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        log("Stream collected");

        bw.write("LOGIN");
        bw.newLine();
        bw.flush();

        String response = br.readLine();
        log("Collected message: " + response);

        if ("ACK".equals(response)) {
            for (int i = 0; i < 100; i++) {
                bw.write("String " + i);
                bw.newLine();
                bw.flush();
            }
            bw.write("QUIT");
            bw.newLine();
            bw.flush();
        }

        bw.close();
        br.close();

        log("Client port closing");
        clientSocket.close();
        log("Client port closed");

    }
}

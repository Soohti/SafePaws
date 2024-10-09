package org.cs3343.safepaws;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

public class Server {
    private static final String KEYSTORE_PATH = "conf/server/keystore.jks";
    private static final char[] KEYSTORE_PASSWORD = "53rV3r54F3P4W5".toCharArray();

    public static void main(String[] args) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(KEYSTORE_PATH), KEYSTORE_PASSWORD);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, KEYSTORE_PASSWORD);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
        try (SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(1234)) {
            System.out.println("Server started and waiting for connections...");
            while (true) {
                try (SSLSocket socket = (SSLSocket) serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    out.println("Welcome to the server!");
                    out.println("Here are some options:");
                    out.println("1. Option 1");
                    out.println("2. Option 2");
                    out.println("3. Option 3");
                    out.println("Type 'EXIT' to disconnect.");
                    out.flush();

                    out.println("READY_FOR_INPUT");

                    String request;
                    while ((request = in.readLine()) != null) {
                        if (request.equalsIgnoreCase("EXIT")) {
                            out.println("Goodbye!");
                            break;
                        }
                        System.out.println("Received: " + request);
                        out.println("Echo: " + request);
                    }
                }
            }
        }
    }
}

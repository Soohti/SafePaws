package org.cs3343.safepaws;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

public class Client {
    private static final String TRUSTSTORE_PATH = "conf/client/truststore.jks";
    private static final char[] TRUSTSTORE_PASSWORD = "SafePaws".toCharArray();

    public static void main(String[] args) throws Exception {
        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(new FileInputStream(TRUSTSTORE_PATH), TRUSTSTORE_PASSWORD);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory factory = sslContext.getSocketFactory();
        try (SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println("Server: " + serverResponse);

                if (serverResponse.equals("READY_FOR_INPUT")) {
                    System.out.print("Your choice: ");
                    String userInput = stdIn.readLine();

                    if (userInput.equalsIgnoreCase("EXIT")) {
                        out.println("EXIT"); // Notify server about the exit
                        System.out.println("Exiting...");
                        break;
                    }

                    out.println(userInput); // Send user input to server
                }
            }
        }
    }
}

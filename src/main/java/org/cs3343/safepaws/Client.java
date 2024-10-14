package org.cs3343.safepaws;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Properties;

public final class Client {
    /**
     * Path to the client properties file.
     */
    private static final String CLIENT_PROPERTIES_PATH =
            "conf/client/client.properties";
    /**
     * Path to the truststore file.
     */
    private static final String TRUSTSTORE_PATH = "conf/client/truststore.jks";
    /**
     * Password for the truststore.
     */
    private static final char[] TRUSTSTORE_PASSWORD = "SafePaws".toCharArray();

    /**
     * Main method of the client.
     *
     * @param args Command line arguments.
     * @throws Exception If an error occurs.
     */
    public static void main(final String[] args) throws Exception {
        Properties clientProperties = new Properties();
        try (FileInputStream input = new FileInputStream(
                CLIENT_PROPERTIES_PATH)) {
            clientProperties.load(input);
        }
        String serverAddress = clientProperties.getProperty("server.address");
        int serverPort =
                Integer.parseInt(clientProperties.getProperty("server.port"));

        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(new FileInputStream(TRUSTSTORE_PATH),
                TRUSTSTORE_PASSWORD);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory factory = sslContext.getSocketFactory();
        try (SSLSocket socket = (SSLSocket) factory.createSocket(serverAddress,
                serverPort);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(),
                             StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream(),
                             StandardCharsets.UTF_8), true);
             BufferedReader stdIn = new BufferedReader(
                     new InputStreamReader(System.in,
                             StandardCharsets.UTF_8))) {

            System.out.println(
                    "Connected to " + serverAddress + ":" + serverPort);
            String serverResponse;
            boolean linebreak = false;
            while ((serverResponse = in.readLine()) != null) {
                if (serverResponse.equals("SYSTEM: READY_FOR_INPUT")) {
                    String userInput = stdIn.readLine();
                    out.println(userInput);
                    linebreak = false;
                } else {
                    if (linebreak) {
                        System.out.println();
                    } else {
                        linebreak = true;
                    }
                    System.out.print(serverResponse);
                }
            }
        }
    }

    private Client() {
        throw new AssertionError("Instantiation not allowed");
    }
}

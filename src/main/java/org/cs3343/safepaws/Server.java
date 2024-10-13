package org.cs3343.safepaws;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.cs3343.safepaws.util.UIExecutor;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.util.Properties;

@SuppressWarnings("InfiniteLoopStatement")
public final class Server {
    /**
     * Path to the server properties file.
     */
    private static final String SERVER_PROPERTIES_PATH =
            "conf/server/server.properties";
    /**
     * Path to the keystore file.
     */
    private static final String KEYSTORE_PATH = "conf/server/keystore.jks";
    /**
     * Password for the keystore.
     */
    private static final char[] KEYSTORE_PASSWORD =
            "53rV3r54F3P4W5".toCharArray();

    /**
     * Main method of the server.
     *
     * @param args Command line arguments.
     * @throws Exception If an error occurs.
     */
    public static void main(final String[] args) throws Exception {
        Properties serverProperties = new Properties();
        try (FileInputStream input = new FileInputStream(
                SERVER_PROPERTIES_PATH)) {
            serverProperties.load(input);
        }
        String listenAddress = serverProperties.getProperty("listen.address");
        int listenPort =
                Integer.parseInt(serverProperties.getProperty("listen.port"));

        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");
        DbManager.init(dbUrl, dbUsername, dbPassword);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(KEYSTORE_PATH), KEYSTORE_PASSWORD);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, KEYSTORE_PASSWORD);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
        try (ServerSocket serverSocket = ssf.createServerSocket(listenPort, 0,
                InetAddress.getByName(listenAddress))) {
            System.out.println(
                    "Server started at " + listenAddress + ":" + listenPort);
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println(
                        "New connection from " + socket.getInetAddress()
                                .getHostAddress() + ":" + socket.getPort());
                new Thread(() -> {
                    try (InputStream in = socket.getInputStream();
                         OutputStream out = socket.getOutputStream()) {
                        Session session = new Session(in, out);
                        UIExecutor uiExecutor = new UIExecutor(session);
                        uiExecutor.start();
                        session.println("SERVER: GOODBYE");
                        System.out.println("Closed connection from "
                                + socket.getInetAddress().getHostAddress() + ":"
                                + socket.getPort());
                    } catch (IOException e) {
                        System.out.println(
                                "Connection from " + socket.getInetAddress()
                                        .getHostAddress() + ":"
                                        + socket.getPort() + " is lost due to "
                                        + e.getMessage());
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            // Ignore
                        }
                    }
                }).start();
            }
        }
    }

    private Server() {
        throw new AssertionError("Instantiation not allowed");
    }
}

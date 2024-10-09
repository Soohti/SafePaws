package org.cs3343.safepaws;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.cs3343.safepaws.util.UIExecutor;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetAddress;
import java.security.KeyStore;
import java.util.Properties;

public class Server {
    private static final String SERVER_PROPERTIES_PATH = "conf/server/server.properties";
    private static final String KEYSTORE_PATH = "conf/server/keystore.jks";
    private static final char[] KEYSTORE_PASSWORD = "53rV3r54F3P4W5".toCharArray();

    public static void main(String[] args) throws Exception {
        Properties serverProperties = new Properties();
        try (FileInputStream input = new FileInputStream(SERVER_PROPERTIES_PATH)) {
            serverProperties.load(input);
        }
        String listenAddress = serverProperties.getProperty("listen.address");
        int listenPort = Integer.parseInt(serverProperties.getProperty("listen.port"));

        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");
        String dbDriver = serverProperties.getProperty("db.driver");
        DbManager.init(dbDriver, dbUrl, dbUsername, dbPassword);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(KEYSTORE_PATH), KEYSTORE_PASSWORD);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, KEYSTORE_PASSWORD);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
        try (SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(listenPort, 50, InetAddress.getByName(listenAddress))) {
            System.out.println("Server started at " + listenAddress + ":" + listenPort);
            //noinspection InfiniteLoopStatement
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println("New connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
                new ClientHandler(socket).start();
            }
        }
    }
}

class ClientHandler extends Thread {
    private final SSLSocket socket;

    public ClientHandler(SSLSocket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            Session session = new Session(in, out);
            UIExecutor uiExecutor = new UIExecutor(session);
            uiExecutor.start();
            session.out.println("SERVER: GOODBYE");
            System.out.println("Closed connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Could not close socket: " + e.getMessage());
            }
        }
    }
}

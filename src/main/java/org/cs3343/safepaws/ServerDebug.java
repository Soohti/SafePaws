package org.cs3343.safepaws;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.cs3343.safepaws.util.UIExecutor;

import java.io.FileInputStream;
import java.util.Properties;

@SuppressWarnings("CallToPrintStackTrace")
public final class ServerDebug {
    /**
     * Path to the server properties file.
     */
    private static final String SERVER_PROPERTIES_PATH =
            "conf/server/server.properties";

    /**
     * Main method of the server (debug mode).
     * Using standard input and output instead of sockets.
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
        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");
        DbManager.init(dbUrl, dbUsername, dbPassword);

        Session session = new Session(System.in, System.out);
        UIExecutor uiExecutor = new UIExecutor(session);
        try {
            uiExecutor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerDebug() {
        throw new AssertionError("Instantiation not allowed");
    }
}

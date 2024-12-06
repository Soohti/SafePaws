package serverclient;

import org.cs3343.safepaws.Client;
import org.cs3343.safepaws.Server;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Server and Client.
 */
public class TestServerClient {
    /**
     * The sleep time in milliseconds to wait for server
     * and client interactions.
     */
    private static final int SLEEP_TIME = 1000;

    /**
     * Tests the successful interaction between Server and Client.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testSuccess() throws Exception {
        // Create a thread for the server
        Thread serverThread = new Thread(() -> {
            try {
                Server.main(new String[0]);
            } catch (Exception ignored) {
            }
        });
        serverThread.start();

        // Wait for the server to start
        Thread.sleep(SLEEP_TIME);

        // Create a thread for the client and override the stdin for the client
        Thread clientThread = new Thread(() -> {
            try {
                System.setIn(new ByteArrayInputStream("E\n".getBytes()));
                Client.main(new String[0]);
            } catch (Exception ignored) {
            }
        });
        clientThread.start();

        // Wait for the client to interact with the server
        Thread.sleep(SLEEP_TIME);
        clientThread.interrupt();

        // Wait for the server to process the client's request
        Thread.sleep(SLEEP_TIME);
        serverThread.interrupt();
    }

    /**
     * Tests the private constructors of Server and Client.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testPrivateConstructor() throws Exception {
        // Create a new instance of the Server class
        Constructor<Server> serverConstructor = Server.class
                .getDeclaredConstructor();
        serverConstructor.setAccessible(true);
        int cntFail = 0;
        try {
            serverConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }

        // Create a new instance of the Client class
        Constructor<Client> clientConstructor = Client.class
                .getDeclaredConstructor();
        clientConstructor.setAccessible(true);
        try {
            clientConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }

        // Assert that both constructors threw an exception
        assertEquals(2, cntFail);
    }
}

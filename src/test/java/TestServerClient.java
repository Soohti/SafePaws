import org.cs3343.safepaws.Client;
import org.cs3343.safepaws.Server;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Constructor;

public class TestServerClient {
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

        Thread.sleep(1000);
        // Create a thread for the client
        // override the stdin for the client
        Thread clientThread = new Thread(() -> {
            try {
                System.setIn(
                        new ByteArrayInputStream("E\n".getBytes()));
                Client.main(new String[0]);
            } catch (Exception ignored) {
            }
        });
        clientThread.start();

        // Wait for 1s and kill both threads
        Thread.sleep(1000);
        clientThread.interrupt();
        Thread.sleep(1000);
        serverThread.interrupt();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        // Create a new instance of the Server class
        Constructor<Server> serverConstructor =
                Server.class.getDeclaredConstructor();
        serverConstructor.setAccessible(true);
        int cntFail = 0;
        try {
            serverConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }

        // Create a new instance of the Client class
        Constructor<Client> clientConstructor =
                Client.class.getDeclaredConstructor();
        clientConstructor.setAccessible(true);
        try {
            clientConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }

        assert cntFail == 2;
    }
}

package ui.admin;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.AllocatePets;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAllocatePets {
    @BeforeEach
    public void setUp() throws Exception {
        Properties serverProperties = new Properties();
        final String SERVER_PROPERTIES_PATH = "conf/server/server.properties";

        try (FileInputStream input = new FileInputStream(
                SERVER_PROPERTIES_PATH)) {
            serverProperties.load(input);
        }

        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");

        DbManager.init(dbUrl, dbUsername, dbPassword);
    }

    @Test
    public void testAllocatePetsV1() {
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        AllocatePets allocatePets = new AllocatePets(referrer);
        UI nextUI = allocatePets.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Allocate Idle Pets to Members", allocatePets.getName());
    }

}

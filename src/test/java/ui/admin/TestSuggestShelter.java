package ui.admin;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.SuggestShelter;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSuggestShelter {
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
    public void testSuggestShelterV1() {
        String inputs = "1\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        SuggestShelter suggestShelter = new SuggestShelter(referrer);
        UI nextUI = suggestShelter.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Recommend New Shelter Locations for Stray Animals",
                suggestShelter.getName());
    }

    @Test
    public void testSuggestShelterV2() {
        String inputs = "2\n8\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        SuggestShelter suggestShelter = new SuggestShelter(referrer);
        UI nextUI = suggestShelter.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Recommend New Shelter Locations for Stray Animals",
                suggestShelter.getName());
    }

    @Test
    public void testSuggestShelterV3() {
        String inputs = "3\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        SuggestShelter suggestShelter = new SuggestShelter(referrer);
        UI nextUI = suggestShelter.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Recommend New Shelter Locations for Stray Animals",
                suggestShelter.getName());
    }
}

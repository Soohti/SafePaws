package menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;
import org.cs3343.safepaws.ui.menu.Menu;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestMenu {
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
    public void testMainMenuV1() throws SQLException {
        UI referrer = new MainMenu();
        class StubMenu extends Menu {
            private final UI[] menuItems = new UI[] {};

            public StubMenu() {
                super("Stub Menu", null, referrer);
                setMenuItems(menuItems);
            }
        }
        UI current = new StubMenu();
        String inputs = "B\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI nextUI = current.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Main Menu", nextUI.getName());
    }
}

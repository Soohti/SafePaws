package menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.AdminMenu;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAdminMenu {
	@BeforeEach
    public void setUp() throws Exception {
        Properties serverProperties = new Properties();
        final String SERVER_PROPERTIES_PATH = "conf/server/server.properties";

        try (FileInputStream input = new FileInputStream(SERVER_PROPERTIES_PATH)) {
            serverProperties.load(input);
        }

        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");

        DbManager.init(dbUrl, dbUsername, dbPassword);
    }
    
	@Test
    public void testAdminMenuV1() throws SQLException {
        String inputs="5\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        AdminMenu adminMenu =new AdminMenu();
        UI nextUI = adminMenu.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Admin Menu", adminMenu.getName());
    }
	
	@Test
    public void testAdminMenuV2() throws SQLException {
        String inputs="T\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        AdminMenu adminMenu =new AdminMenu();
        UI nextUI = adminMenu.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Admin Menu", adminMenu.getName());
    }
	@Test
    public void testAdminMenuV3() throws SQLException {
        String inputs="100\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        AdminMenu adminMenu =new AdminMenu();
        UI nextUI = adminMenu.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Admin Menu", adminMenu.getName());
    }
}

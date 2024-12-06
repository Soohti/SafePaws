package SystemTest;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMainMenu {
    Connection conn;

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
        conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @Test
    public void testMainMenuV1() throws SQLException {
        String inputs = "2\nmemberm1\nmemberm1\n"
                + "3\n5\n5\n100\n8\n4\n7\n5000\nDog\nBeagle\nf\n"
                + "5\n2\n1\n2\nadminaaa\nadminaaa\n3\nE\n4\n1\n5\nE2\n"
                + "Blackkk\nDog\nDog\n4\n30\nm\n5\n5\n1\n2\nshelter1\nshelter1\n"
                + "2\n1\n1\n3\n1 2 3 4 5\n1\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI cur = new MainMenu();
        int cnt = 0;
        while (cur != null) {
            cur = cur.getNextUI(session);
            cnt++;
        }
        assertEquals(36, cnt);
        String preUpdateSQL = "DELETE FROM PET WHERE Name = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkk");
        pstmt.executeUpdate();
    }
}

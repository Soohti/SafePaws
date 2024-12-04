package ui.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.AddPet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAddPet {
	Connection conn; 
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
        conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
    
	@Test
    public void testAddPetV1() throws SQLException {
        String inputs="Blackkk\nDog\nDog\n4\n30\nm\n5\n5\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        AddPet addPet =new AddPet(referrer);
        UI nextUI = addPet.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Add one pet to database", addPet.getName());
        String preUpdateSQL = "DELETE FROM PET WHERE Name = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkk");
        pstmt.executeUpdate();
    }
	
	@Test
    public void testAddPetV2() throws SQLException {
        String inputs="Petttttttttttttttttttttttttttttt\nBlackkk\n"
        		+ "Dogggggggggggggggggggggggggggggggggg\nDog\n"
        		+ "Dogggggggggggggggggggggggggggggggggg\nDog\n"
        		+ "-1\n4\n-1\n30\na\nm\n100\n5\n100\n5\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        AddPet addPet =new AddPet(referrer);
        UI nextUI = addPet.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Add one pet to database", addPet.getName());
        String preUpdateSQL = "DELETE FROM PET WHERE Name = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkk");
        pstmt.executeUpdate();
    }
	
}

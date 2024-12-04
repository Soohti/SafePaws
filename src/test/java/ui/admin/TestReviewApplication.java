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
import org.cs3343.safepaws.ui.admin.ReviewApplication;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestReviewApplication {
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
    public void testReviewApplicationV1() {
        String inputs="V\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication =new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications", reviewApplication.getName());
    }
	
	@Test
    public void testReviewApplicationV2() {
        String inputs="20\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication =new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications", reviewApplication.getName());
    }
	
	@Test
    public void testReviewApplicationV3() {
        String inputs="0\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication =new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications", reviewApplication.getName());
    }
	
	@Test
    public void testReviewApplicationV4() throws SQLException {
        String inputs="21\n1\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication =new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications", reviewApplication.getName());
        String preUpdateSQL = "UPDATE APPLICATION SET State = 0 "
                + "WHERE Id = 21";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.executeUpdate();
    }
	
	@Test
    public void testReviewApplicationV5() {
        String inputs="B\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication =new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications", reviewApplication.getName());
    }
	
	@Test
    public void testReviewApplicationV6() throws SQLException {
        String inputs="21\n5\n1\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication =new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications", reviewApplication.getName());
        String preUpdateSQL = "UPDATE APPLICATION SET State = 0 "
                + "WHERE Id = 21";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.executeUpdate();
    }
}

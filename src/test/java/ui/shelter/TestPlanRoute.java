package ui.shelter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.shelter.PlanRoute;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlanRoute {
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
    public void testPlanRouteV1() throws SQLException {
        String inputs="1 2\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation(1,5,5);
        Shelter shelter = new Shelter(1,"Blackkkk","Blackkkk","S",shelterLocation);
        session.setAccount(shelter);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        PlanRoute planRoute =new PlanRoute(referrer);
        UI nextUI = planRoute.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Plan a Shortest Route to Collect Stray Animals", planRoute.getName());
    }
    @Test
    public void testPlanRouteV2() throws SQLException {
        String inputs="1 20\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation(1,5,5);
        Shelter shelter = new Shelter(1,"Blackkkk","Blackkkk","S",shelterLocation);
        session.setAccount(shelter);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        PlanRoute planRoute =new PlanRoute(referrer);
        UI nextUI = planRoute.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Plan a Shortest Route to Collect Stray Animals", planRoute.getName());
    }
}
package ui.account;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestLogout {
    /**
     * Tests the constructor of the {@link Logout} class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testConstructor() {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };

        // Act
        Logout logout = new Logout(referrer);

        // Assert
        assertNotNull(logout);
        assertEquals("Log Out", logout.getName());
        assertEquals(referrer, logout.getReferrer());
    }

    /**
     * Tests the getNextUI method of the {@link Logout} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV1() throws Exception {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Logout logout = new Logout(referrer);

        // Act
        UI nextUI = logout.getNextUI(session);

        // Assert
        assertEquals("Main Menu", nextUI.getName());
    }
}

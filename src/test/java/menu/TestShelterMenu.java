package menu;

import org.cs3343.safepaws.ui.menu.ShelterMenu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShelterMenu {

    /**
     * Tests the constructor of the MemberMenu class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testMemberMenuConstructor() {
        // Act
        ShelterMenu shelterMenu = new ShelterMenu();

        // Assert
        assertEquals("Shelter Menu", shelterMenu.getName());
    }
}

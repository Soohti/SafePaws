package menu;

import org.cs3343.safepaws.ui.menu.MemberMenu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMemberMenu {

    /**
     * Tests the constructor of the MemberMenu class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testMemberMenuConstructor() {
        // Act
        MemberMenu memberMenu = new MemberMenu();

        // Assert
        assertEquals("Member Menu", memberMenu.getName());
    }
}

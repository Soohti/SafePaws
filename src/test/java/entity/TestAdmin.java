package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cs3343.safepaws.entity.Admin;
import org.junit.jupiter.api.Test;

public class TestAdmin {
	/**
     * Tests the parameterized constructor of the {@code Admin} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor() {
        Admin admin = new Admin(1, "username",
                "password", "A");

        assertEquals(1, admin.getId());
        assertEquals("username", admin.getUsername());
        assertEquals("password", admin.getPassword());
        assertEquals("A", admin.getRole());
    }
    
    /**
     * Tests the clone method of the {@code Admin} class.
     * Ensures that a deep copy of the member object is created.
     */
    @Test
    public void testClone() {
        Admin admin = new Admin(1,
                "username", "password", "A");
        Admin clonedAdmin = admin.clone();

        assertEquals(admin.getId(), clonedAdmin.getId());
        assertEquals(admin.getUsername(), clonedAdmin.getUsername());
        assertEquals(admin.getPassword(), clonedAdmin.getPassword());
        assertEquals(admin.getRole(), clonedAdmin.getRole());
    }
	
}

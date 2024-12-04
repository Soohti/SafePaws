package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cs3343.safepaws.entity.Account;
import org.junit.jupiter.api.Test;
public class TestAccount {
	/**
     * Tests the default constructor of the {@code Account} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        Account account = new Account();
        assertEquals(-1, account.getId());
        assertEquals("", account.getUsername());
        assertEquals("", account.getPassword());
        assertEquals("", account.getRole());
    }

    /**
     * Tests the parameterized constructor of the {@code Account} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor1() {
        Account account = new Account(1, "username",
                "password", "M");

        assertEquals(1, account.getId());
        assertEquals("username", account.getUsername());
        assertEquals("password", account.getPassword());
        assertEquals("M", account.getRole());
    }
    
    /**
     * Tests the parameterized constructor of the {@code Account} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor2() {
        Account account = new Account("username",
                "password", "M");

        assertEquals(-1, account.getId());
        assertEquals("username", account.getUsername());
        assertEquals("password", account.getPassword());
        assertEquals("M", account.getRole());
    }

    /**
     * Tests the setter and getter methods of the {@code Account} class.
     * Ensures that all fields are set and retrieved correctly.
     */
    @Test
    public void testSettersAndGetters() {
        Account account = new Account();
        account.setId(2);
        account.setUsername("newUsername");
        account.setPassword("newPassword");
        account.setRole("M");
        assertEquals(2, account.getId());
        assertEquals("newUsername", account.getUsername());
        assertEquals("newPassword", account.getPassword());
        assertEquals("M", account.getRole());
    }

    /**
     * Tests the clone method of the {@code Account} class.
     * Ensures that a deep copy of the member object is created.
     */
    @Test
    public void testClone() {
        Account account = new Account(1,
                "username", "password", "M");
        Account clonedAccount = account.clone();

        assertEquals(account.getId(), clonedAccount.getId());
        assertEquals(account.getUsername(), clonedAccount.getUsername());
        assertEquals(account.getPassword(), clonedAccount.getPassword());
        assertEquals(account.getRole(), clonedAccount.getRole());
    }
}

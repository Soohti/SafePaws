package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.util.DbManager;

import java.sql.SQLException;
import java.util.*;
import org.cs3343.safepaws.ui.UI;

public class CreateAccount extends UI{
	
	private void createAccount(Scanner scanner, String role) {
        System.out.println("Enter a username:");
        String username = scanner.nextLine();

        System.out.println("Enter a password:");
        String password = scanner.nextLine();

        Account account = new Account(username, password, role);
        try {
            DbManager.insertAccount(account);
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }
}

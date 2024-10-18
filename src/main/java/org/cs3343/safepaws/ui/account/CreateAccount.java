package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import org.cs3343.safepaws.ui.UI;

public class CreateAccount extends UI{

	private static final String NAME = "Create Account";
	
	@Override
    public UI execute(Session session) throws IOException{
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter a username:");
        String username = scanner.nextLine();

        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        
        System.out.println("Enter your role:");
        String role = scanner.nextLine();

        Account account = new Account(username, password, role);
        try {
            DbManager.insertAccount(account);
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
        
        
        return this.getReferrer();
    }
    
    
    public CreateAccount(final UI pReferrer) {
		super(NAME,pReferrer);
    }
 
    @Override
    public boolean isVisibleTo(final Session session) {
        return false;
    }
}

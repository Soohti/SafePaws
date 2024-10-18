package org.cs3343.safepaws.ui.account;


import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Login extends UI{
	
	private static final String NAME = "Log In";
	/* make connection with session */
	@Override
	public UI execute(Session session) throws IOException{
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        
        try {
        	if(DbManager.authenticateUser(username, password)) {
        		System.out.println("Log in successfully.");
        		// 根据角色跳转到不同的界面或功能
        		Account account=DbManager.selectAccount(username);
        		session.setAccount(account);
        	}
        	else {
        		System.out.println("Your password is false.");
        	}
        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
        
        return this.getReferrer();
	}
	
	public Login(final UI pReferrer) {
		super(NAME,pReferrer);
    }
	
	@Override
	public boolean isVisibleTo(final Session session) {
        return true;
    }

}

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
	
	/**
	 * Execute.
	 *
	 * @param session the session
	 * @return the ui
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public UI execute(Session session) throws IOException{
    	session.println("Enter your username:");
        String username = session.requestInput();

        session.println("Enter your password:");
        String password = session.requestInput();
        
        try {
        	if(DbManager.authenticateUser(username, password)) {
        		session.println("Log in successfully.");
        		// 根据角色跳转到不同的界面或功能
        		Account account=DbManager.selectAccount(username);
        		session.setAccount(account);
        	}
        	else {
        		session.println("Your username or password is false.");
        	}
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }
        
        return this.getReferrer();
	}
	
	/**
	 * Instantiates a new login.
	 *
	 * @param pReferrer the referrer
	 */
	public Login(final UI pReferrer) {
		super(NAME,pReferrer);
    }
	
	/**
	 * Checks if is visible to.
	 *
	 * @param session the session
	 * @return true, if is visible to
	 */
	@Override
	public boolean isVisibleTo(final Session session) {
        return true;
    }

}

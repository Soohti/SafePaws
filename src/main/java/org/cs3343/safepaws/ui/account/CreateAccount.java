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
	
	/**
	 * Execute.
	 *
	 * @param session the session
	 * @return the ui
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
    public UI execute(Session session) throws IOException{
    	session.println("Enter a username:");
        String username = session.requestInput();

        session.println("Enter a password:");
        String password = Account.encryptPassword(session.requestInput());
        
        session.println("Enter your role:");
        String role = session.requestInput();

        Account account = new Account(username, password, role);
        try {
            DbManager.insertAccount(account);
            session.println("Account created successfully.");
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }
        
        session.setAccount(account);
        
        return this.getReferrer();
    }
    
    
    /**
     * Instantiates a new creates the account.
     *
     * @param pReferrer the referrer
     */
    public CreateAccount(final UI pReferrer) {
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

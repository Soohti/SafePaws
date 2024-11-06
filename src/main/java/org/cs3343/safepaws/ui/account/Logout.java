package org.cs3343.safepaws.ui.account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class Logout extends UI{
	
	private static final String NAME = "Log Out";
	
	/**
	 * Execute.
	 *
	 * @param session the session
	 * @return the ui
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public UI execute(Session session) throws IOException{
		session.println("Bye bye.");
		
		session.clear();
        return this.getReferrer();
    }
    
	
    /**
     * Instantiates a new logout.
     *
     * @param pReferrer the referrer
     */
    public Logout(final UI pReferrer) {
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
        return session.getAccount() != null;
    }
}

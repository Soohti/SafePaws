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
	/* make connection with session */
	@Override
	public UI execute(Session session) throws IOException{
		System.out.println("Bye bye.");
		
		
        return this.getReferrer();
    }
    
	
    public Logout(final UI pReferrer) {
		super(NAME,pReferrer);
    }

    @Override
    public boolean isVisibleTo(final Session session) {
        return false;
    }
}

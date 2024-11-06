package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;
import java.sql.SQLException;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class SetProfile extends UI {
	
	private static final String NAME = "Set Profile";
		
    public SetProfile(UI referrer) {
        super("Set Profile", referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
    	int extroversionLevel;
    	int dailyActivityLevel;
    	int houseSize;
    	int workHours;
    	int numberOfFamilyMembers;
    	int previousPetExperience;
    	int financialBudget;
    	String preferredBreed;
    	String gender;
    	session.println("Enter your extroversionLevel:");
    	//extroversionLevel = session.requestInput();
        

//        session.println("Enter a password:");
//        String password = Account.encryptPassword(session.requestInput());
//        
//        session.println("Enter your role:");
//        String role = session.requestInput();
//
//        Account account = new Account(username, password, role);
//        try {
//            DbManager.insertAccount(account);
//            session.println("Account created successfully.");
//        } catch (SQLException e) {
//            session.println("Error creating account: " + e.getMessage());
//        }
//        
//        session.setAccount(account);
        
    	
        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}

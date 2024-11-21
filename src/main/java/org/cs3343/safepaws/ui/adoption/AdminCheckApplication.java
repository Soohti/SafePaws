package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

public class AdminCheckApplication extends UI {   //a pet can only be assigned to one member
	                                              //if approved, pet should be erased
	                                              //all application for this pet should be rejected
    private static final String NAME =
            "Admin check one application for adoption";

    public AdminCheckApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        session.println("Enter the id of application you want to check that has not been checked.:");
        int Aid = Integer.parseInt(session.requestInput());
        while (!Application.isValidAid(Aid)) {
            session.println("Your input application id is invalid. Please enter again:");
            Aid = Integer.parseInt(session.requestInput());
        }
        
        session.println(
                "Enter what state you want to set (1: approve; 2: reject):");
        int state = Integer.parseInt(session.requestInput());
        while (!Application.isValidState(state)) {
            session.println("Your input state is invalid. Please enter again:");
            state = Integer.parseInt(session.requestInput());
        }

        DbManager.changeState(Aid, state);

        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(
                session.getAccount().getRole());
    }
}

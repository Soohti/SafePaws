package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class AdminSeeAllApplication extends UI{
	private static final String NAME = "Admin see all applications for adoption";

    public AdminSeeAllApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) {
    	ArrayList<Application> applications = new ArrayList<>();
    	try {
    		applications=DbManager.ViewAllApplication();
    		System.out.printf("%-10s %-10s %-10s %-10s", "Id","Mid","Pid","State");
            System.out.println("-------------------------------------------------------------");
            
            for(Application a:applications) {
            	System.out.printf("%-10s %-10d %-10s %-10s",a.getId(),a.getUser().getId(),a.getPet().getId(),a.getState() );
            }
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }
        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

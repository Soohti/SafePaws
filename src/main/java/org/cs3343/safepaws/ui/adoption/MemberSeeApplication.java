package org.cs3343.safepaws.ui.adoption;
import org.cs3343.safepaws.entity.*;
import java.util.List;
import java.io.IOException;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class MemberSeeApplication extends UI{
	private static final String NAME = "Member see submitted applications for adoption";

    public MemberSeeApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
    	Member member = (Member) session.getAccount();
    	List<Application> applications = DbManager.selectApplicationByMember(member);
    	String state[]= {"Pending","Approved","Rejected"};
    	
        if (applications.isEmpty()) {
            session.println("You have not submitted any adoption applications.");
        } else {
        	for (Application application : applications) {
                Pet pet = application.getPet();
                String petInfo = "Pet Name: " + pet.getName() + ", Species: " + pet.getSpecies();
                String status = state[application.getState()];
                session.println(petInfo + " | Status: " + status);
            }
        }
        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}
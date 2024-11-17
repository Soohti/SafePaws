package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;

public class AdminViewDetailApplication extends UI{
	private static final String NAME = "Admin view one application for detail";

    public AdminViewDetailApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        // select an application and check whether yes or no
    	session.println("Enter the application you want to see:");
    	
    	int Aid = Integer.parseInt(session.requestInput());
    	Application application = DbManager.selectApplication(Aid);
    	Member m=application.getUser();
    	Pet p=application.getPet();
    	MemberProfile pf=m.getProfile();
    	
    	session.printf("%-5s %-20s %-20s %-20s %-20s %-15s %-15s %-25s %-25s %-20s %-10s","Id","PreferredSpecies", "PreferredBreed","ExtroversionLevel","DailyActivityLevel","HouseSize","WorkHours","NumberOfFamilyMembers","PreviousPetExperience","FinancialBudget","Gender");
    	session.println("");
    	session.printf("%-5d %-20s %-20s %-20d %-20d %-15d %-15d %-25d %-25d %-20d %-10s",m.getId(),pf.getPreferredSpecies(),pf.getPreferredBreed(),pf.getExtroversionLevel(),pf.getDailyActivityLevel(),pf.getHouseSize(),pf.getWorkHours(),pf.getNumberOfFamilyMembers(),pf.getPreviousPetExperience(),pf.getFinancialBudget(),pf.getGender());
    	session.println("");
    	
    	session.println("");
        session.printf("%-5s %-15s %-15s %-15s %-5s %-10s %-6s %-15s %-15s", "Id","Name", "Species", "Breed", "Age", "Weight", "Gender", "ActivityLevel", "HealthStatus");
        session.println("");
        session.printf("%-5d %-15s %-15s %-15s %-5d %-10d %-6s %-15d %-15d", p.getId(),p.getName(),p.getSpecies(), p.getBreed(), p.getAge(), p.getWeight(), p.getGender(), p.getActivityLevel(), p.getHealthStatus());
        session.println("");
        
        session.println("");
        session.printf("%-10s %10s","Score","State");
        session.println("");
        session.printf("%-10f %10d",PetMatchingAlgo.calculateMatch(m, p),application.getState());
        session.println("");
        
        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class AdminViewDetailApplication extends UI{
	private static final String NAME = "Admin view one application for detail";

    public AdminViewDetailApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        // select an application and check whether yes or no
    	session.println("Enter the application you want to check:");
    	
    	int Aid = Integer.parseInt(session.requestInput());
    	Application application = DbManager.selectApplication(Aid);
    	
    	// 输出表头
        System.out.printf("%-15s %-5s %-10s %-6s %-15s %-15s%n", "Breed", "Age", "Size", "Gender", "ActivityLevel", "HealthStatus");
        System.out.println("-------------------------------------------------------------");
        
        
        //System.out.printf("%-15s %-5d %-10s %-6s %-15s %-15s%n", breed, age, size, gender, activityLevel, healthStatus);
    	
        return this.getReferrer(); // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class AdminCheckApplication extends UI{
	private static final String NAME = "Admin check one application for adoption";

    public AdminCheckApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        // select an application and check whether yes or no
    	session.println("Enter the application you want to check:");
    	
    	int Aid = Integer.parseInt(session.requestInput());
    	
    	session.println("Enter what state you want to set (1: approve; 2: reject):");
    	int state = Integer.parseInt(session.requestInput());
    	
    	DbManager.changeState(Aid,state);
    	
        return this.getReferrer(); // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

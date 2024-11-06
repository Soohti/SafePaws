package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

public class AdminSeeAllApplication extends UI{
	private static final String NAME = "Admin see all applications for adoption";

    public AdminSeeAllApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        // select all applications and check whether yes or no
    	
        return this.getReferrer(); // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

public class SubmitApplication extends UI {
	
	private static final String NAME = "Submit an application";

    public SubmitApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        
        return this.getReferrer(); // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}

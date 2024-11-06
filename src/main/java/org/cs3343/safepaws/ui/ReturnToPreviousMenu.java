package org.cs3343.safepaws.ui;

import java.io.IOException;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

public class ReturnToPreviousMenu extends UI {
	private static final String NAME = "Return to previous menu";
	
	public ReturnToPreviousMenu(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        // select all applications and check whether yes or no
    	
        return this.getReferrer(); // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null;
    }
    
}

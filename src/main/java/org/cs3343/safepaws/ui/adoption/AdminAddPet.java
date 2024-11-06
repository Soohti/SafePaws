package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

public class AdminAddPet extends UI{
	private static final String NAME = "Admin Add one pet to database";

    public AdminAddPet(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        // select all applications and check whether yes or no
    	
        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

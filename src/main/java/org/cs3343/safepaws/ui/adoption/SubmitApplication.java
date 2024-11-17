package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.*;
import org.cs3343.safepaws.ui.*;
import org.cs3343.safepaws.util.*;

import java.io.IOException;
import java.sql.SQLException;

public class SubmitApplication extends UI {

    private static final String NAME = "Submit an application";

    public SubmitApplication(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        if (!(session.getAccount() instanceof Member)) {
            session.println("Only members can submit an application.");
            return this.getReferrer();
        }

        Member user = (Member) session.getAccount();

        session.println("Enter the ID of the pet you want to apply for:");
        String userInput = session.requestInput();
        int petId = Integer.parseInt(userInput);
        Pet pet;
        try {
            pet = DbManager.selectPetById(petId);
        } catch (SQLException e) {
            pet = null;
            e.printStackTrace();
        }
        int state = 0;
        if (pet != null) {
            Application application = new Application(user, pet, state);
            try {
                DbManager.insertApplication(application);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.getReferrer();
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}
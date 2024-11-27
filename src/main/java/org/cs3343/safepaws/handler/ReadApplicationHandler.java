package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.util.DbManager;

import java.util.ArrayList;
import java.util.Map;

public final class ReadApplicationHandler {
    private static ReadApplicationHandler instance;

    private ReadApplicationHandler() {
    }

    public static ReadApplicationHandler getInstance() {
        if (instance == null) {
            instance = new ReadApplicationHandler();
        }
        return instance;
    }
    //TODO
    public ArrayList<Application> viewAllApplication() {
        try {
            ArrayList<Application> applications = DbManager.readAll(Application.class,
                    "APPLICATION");
            for(Application application : applications) {
                application.set
            }
        } catch (Exception ex) {
            System.out.println("Error during Logging in: "
                    + ex.getMessage());
        }
        return null;
    }

    public Application viewConditionalApplication(final int aid) {
        try {
            return (Application) DbManager.readWithCondition(Application.class,
                    "APPLICATION", Map.of("Id", String.valueOf(aid)));
        } catch (Exception ex) {
            System.out.println("Error during Logging in: "
                    + ex.getMessage());
        }
        return null;
    }
}

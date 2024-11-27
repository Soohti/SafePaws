package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Singleton handler for reading application information.
 */
public final class ReadApplicationHandler {
    /**
     * The single instance of the handler.
     */
    private static ReadApplicationHandler instance;

    /**
     * Private constructor to prevent instantiation.
     */
    private ReadApplicationHandler() {
    }

    /**
     * Gets the single instance of the handler.
     *
     * @return the instance of ReadApplicationHandler
     */
    public static ReadApplicationHandler getInstance() {
        if (instance == null) {
            instance = new ReadApplicationHandler();
        }
        return instance;
    }

    /**
     * Finds all applications.
     *
     * @return a list of all applications, or null if an error occurs
     */
    public ArrayList<Application> findAllApplication() {
        try {
            return DbManager.getInstance().readAll(Application.class,
                    "APPLICATION");
        } catch (Exception ex) {
            System.out.println("Error during finding specific application: "
                    + ex.getMessage());
        }
        return null;
    }

    /**
     * Finds an application by its ID.
     *
     * @param aid the application ID
     * @return the application with the specified ID, or null if not found
     */
    public Application findApplicationByAid(final int aid) {
        try {
            return (DbManager.getInstance()
                    .readWithCondition(Application.class,
                            "APPLICATION",
                            Map.of("Id", String.valueOf(aid)))).getFirst();
        } catch (Exception ex) {
            System.out.println("Error during finding all applications: "
                    + ex.getMessage());
        }
        return null;
    }

    /**
     * Changes the state of an application.
     *
     * @param aid   the application ID
     * @param pid   the pet ID
     * @param state the new state of the application
     */
    public void changeApplicationState(final int aid,
                                       final int pid,
                                       final Application.State state) {
        try {
            if (state == Application.State.APPROVED) {
                DbManager.getInstance().update(Application.class,
                        "APPLICATION",
                        Map.of("State", String.valueOf(Application
                                .State.REJECTED.ordinal())),
                        Map.of("Pid", String.valueOf(pid))
                );
                DbManager.getInstance().update(Pet.class,
                        "PET",
                        Map.of("State", String.valueOf(Application
                                .State.APPROVED.ordinal())),
                        Map.of("Id", String.valueOf(pid))
                );
            }
            DbManager.getInstance().update(Application.class,
                    "APPLICATION",
                    Map.of("State", String.valueOf(state.ordinal())),
                    Map.of("Id", String.valueOf(aid))
            );
        } catch (Exception ex) {
            System.out.println("Error during changing status: "
                    + ex.getMessage());
        }
    }
}

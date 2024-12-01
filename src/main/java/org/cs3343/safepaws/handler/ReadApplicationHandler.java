package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Singleton handler for reading application information.
 */
public final class ReadApplicationHandler {

    /**
     * Public constructor for instantiation.
     */
    public ReadApplicationHandler() {
    }

    /**
     * Finds all applications.
     *
     * @return a list of all applications, or null if an error occurs
     */
    public ArrayList<Application> findAllApplication() {
        try {
            return DbManager.readAll(Application.class,
                    TableSchema.Name.APPLICATION);
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
            try {
                return (DbManager
                        .readWithCondition(Application.class,
                                TableSchema.Name.APPLICATION,
                                Map.of(TableSchema.Column.Id,
                                        String.valueOf(aid)))).getFirst();
            } catch (NoSuchElementException ex) {
                return null;
            }
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
                DbManager.update(Application.class,
                        TableSchema.Name.APPLICATION,
                        Map.of(TableSchema.Column.PId,
                                String.valueOf(pid)),
                        Map.of(TableSchema.Column.State,
                                String.valueOf(Application
                                        .State.REJECTED.ordinal()))
                );
                DbManager.update(Pet.class,
                        TableSchema.Name.PET,
                        Map.of(TableSchema.Column.Id, String.valueOf(pid)),
                        Map.of(TableSchema.Column.State,
                                String.valueOf(Application
                                        .State.APPROVED.ordinal()))
                );
            }
            DbManager.update(Application.class,
                    TableSchema.Name.APPLICATION,
                    Map.of(TableSchema.Column.Id, String.valueOf(aid)),
                    Map.of(TableSchema.Column.State,
                            String.valueOf(state.ordinal()))
            );
        } catch (Exception ex) {
            System.out.println("Error during changing status: "
                    + ex.getMessage());
        }
    }
}

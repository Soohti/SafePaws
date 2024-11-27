package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.util.DbManager;

import java.util.ArrayList;

public final class ReadLocationPointHandler {
    /**
     * The single instance of the handler.
     */

    private static ReadLocationPointHandler instance;

    private ReadLocationPointHandler() {
    }
    /**
     * Gets the single instance of the handler.
     *
     * @return the instance of ReadLocationPointHandler
     */

    public static ReadLocationPointHandler getInstance() {
        if (instance == null) {
            instance = new ReadLocationPointHandler();
        }
        return instance;
    }
    /**
     * Finds all pet location points.
     *
     * @return a list of all pet location points, or null if an error occurs
     */

    public ArrayList<LocationPoint> findAllPet() {
        try {
            return DbManager.getInstance().readAll(LocationPoint.class,
                    "ANIMAL_LOCATION\n");
        } catch (Exception ex) {
            System.out.println("Error during finding all animal location: "
                    + ex.getMessage());
        }
        return null;
    }
}

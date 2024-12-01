package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.util.DbManager;

import java.util.ArrayList;

public final class ReadLocationPointHandler {

    /**
     * Public constructor for instantiation.
     */
    public ReadLocationPointHandler() {
    }
    /**
     * Finds all pet location points.
     *
     * @return a list of all pet location points, or null if an error occurs
     */

    public ArrayList<LocationPoint> findAllPet() {
        try {
            return DbManager.readAll(LocationPoint.class,
                    "ANIMAL_LOCATION\n");
        } catch (Exception ex) {
            System.out.println("Error during finding all animal location: "
                    + ex.getMessage());
        }
        return null;
    }
}

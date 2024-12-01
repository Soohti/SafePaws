package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class PlanRouteHandler {

    /**
     * Public constructor for instantiation.
     */
    public PlanRouteHandler() {
    }

    /**
     * Finds all applications.
     *
     * @param verified the verified of location point
     * @return a list of all location point, or null if an error occurs
     */
    public List<LocationPoint> findAllLocationPoints(
            final boolean verified) {
        try {
            try {
                return DbManager.readWithCondition(LocationPoint.class,
                        TableSchema.Name.ANIMAL_LOCATION,
                        Map.of(TableSchema.Column.Verified,
                                String.valueOf(verified)));
            } catch (NoSuchElementException e) {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error during finding specific application: "
                    + ex.getMessage());
        }
        return null;
    }
}

package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.util.Map;

public final class SetLocationHandler {
    /**
     * Public constructor for instantiation.
     */
    public SetLocationHandler() {
    }

    /**
     * Inserts a shelter location into the database.
     *
     * @param shelter  the shelter to insert the location for.
     * @param location the location to insert.
     */
    public void updateShelterLocation(final Shelter shelter,
                                             final LocationPoint location) {
        try {
            DbManager.update(ShelterLocation.class,
                    TableSchema.Name.SHELTER_LOCATION,
                    Map.of(TableSchema.Column.Id,
                            String.valueOf(shelter.getId())),
                    Map.of(TableSchema.Column.XValue,
                            String.valueOf(location.getxValue()),
                            TableSchema.Column.YValue,
                            String.valueOf(location.getyValue()))
            );
        } catch (Exception ex) {
            System.out.println("Error during inserting shelter location: "
                    + ex.getMessage());
        }

    }

}

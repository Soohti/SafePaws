package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.sql.SQLException;
import java.util.Map;

/**
 * The UpdateLocationHandler class provides the functionality to update
 * a shelter location.
 */
public final class UpdateLocationHandler {
    /**
     * Public constructor for instantiation.
     */
    public UpdateLocationHandler() {
    }

    /**
     * Updates a shelter location into the database.
     *
     * @param location the shelter location to update
     */
    public void updateShelterLocation(final ShelterLocation location) {
        try {
            DbManager.update(ShelterLocation.class,
                    TableSchema.Name.SHELTER_LOCATION,
                    Map.of(TableSchema.Column.Id,
                            String.valueOf(location.getId())),
                    Map.of(TableSchema.Column.XValue,
                            String.valueOf(location.getXValue()),
                            TableSchema.Column.YValue,
                            String.valueOf(location.getYValue()))
            );
        } catch (SQLException ex) {
            System.out.println("Error during inserting shelter location: "
                    + ex.getMessage());
        }

    }

}

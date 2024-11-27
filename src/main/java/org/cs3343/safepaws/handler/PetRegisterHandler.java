package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;

import java.sql.SQLException;
import java.util.Map;

public class PetRegisterHandler {
    /**
     * The index for health status.
     */
    private static final int INDEX_HEALTH = 3;
    private static PetRegisterHandler instance;

    private PetRegisterHandler() {
    }

    public static PetRegisterHandler getInstance() {
        if (instance == null) {
            instance = new PetRegisterHandler();
        }
        return instance;
    }
    /**
     * Inserts a pet into the database.
     *
     * @param name the pet to insert.
     * @throws SQLException if a database error occurs.
     */
    public void insertPetRecord(final String name,
                                final String species,
                                final String breed,
                                final String gender,
                                final int[] numeric,
                                final int sta
                                )
            throws Exception {
        DbManager.getInstance().insertWithAutoValue(
                Map.of("Name", name,
                        "Species", species,
                        "Breed", breed,
                        "Gender", gender,
                        "Age", String.valueOf(numeric[0]),
                        "Weight", String.valueOf(numeric[1]),
                        "ActivityLevel", String.valueOf(numeric[2]),
                        "HealthStatus", String.valueOf(numeric[INDEX_HEALTH]),
                        "Sta", String.valueOf(sta)
                        ),
                "PET");
        System.out.println("Pet inserted successfully");
    }
}

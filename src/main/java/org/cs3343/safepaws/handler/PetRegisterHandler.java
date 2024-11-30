package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.util.DbManager;

import java.util.Map;

/**
 * Singleton handler for registering pet information.
 */
public final class PetRegisterHandler {
    /**
     * The index for health status.
     */
    private static final int INDEX_HEALTH = 3;

    /**
     * Public constructor for instantiation.
     */
    public PetRegisterHandler() {
    }

    /**
     * Inserts a new pet record into the database.
     *
     * @param name     the name of the pet
     * @param species  the species of the pet
     * @param breed    the breed of the pet
     * @param gender   the gender of the pet
     * @param numeric  an array of numeric attributes (age, weight,
     *                 activity level, health status)
     * @param sta      the status of the pet
     * @throws Exception if an error occurs during the database operation
     */
    public void insertPetRecord(final String name,
                                final String species,
                                final String breed,
                                final String gender,
                                final int[] numeric,
                                final int sta)
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

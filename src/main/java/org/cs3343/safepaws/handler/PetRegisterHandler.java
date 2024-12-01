package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

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
     * @param name    the name of the pet
     * @param species the species of the pet
     * @param breed   the breed of the pet
     * @param gender  the gender of the pet
     * @param numeric an array of numeric attributes (age, weight,
     *                activity level, health status)
     * @param sta     the status of the pet
     * @throws Exception if an error occurs during the database operation
     */
    public void insertPetRecord(final String name,
                                final String species,
                                final String breed,
                                final String gender,
                                final int[] numeric,
                                final int sta)
            throws Exception {
        DbManager.insertWithAutoValue(
                Map.of(TableSchema.Column.Name, name,
                        TableSchema.Column.Species, species,
                        TableSchema.Column.Breed, breed,
                        TableSchema.Column.Gender, gender,
                        TableSchema.Column.Age, String.valueOf(numeric[0]),
                        TableSchema.Column.Weight, String.valueOf(numeric[1]),
                        TableSchema.Column.ActivityLevel,
                        String.valueOf(numeric[2]),
                        TableSchema.Column.HealthStatus,
                        String.valueOf(numeric[INDEX_HEALTH]),
                        TableSchema.Column.State, String.valueOf(sta)
                ),
                TableSchema.Name.PET);
        System.out.println("Pet inserted successfully");
    }
}

package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;

import java.sql.SQLException;

public class PetRegisterHandler {

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
     * @param thisPet the pet to insert.
     * @throws SQLException if a database error occurs.
     */
    public void insertPetRecord(final Pet thisPet) throws Exception {
        DbManager.insert(thisPet, "PET");
        System.out.println("Pet inserted successfully");
    }
}

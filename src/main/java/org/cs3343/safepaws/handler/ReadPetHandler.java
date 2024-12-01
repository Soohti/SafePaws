package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.util.ArrayList;
import java.util.Map;

/**
 * Singleton handler for reading pet information.
 */
public final class ReadPetHandler {

    /**
     * Public constructor for instantiation.
     */
    public ReadPetHandler() {
    }

    /**
     * Finds a pet with the specified ID.
     *
     * @param pid the ID of the pet
     * @return the pet with the specified ID, or null if an error occurs
     */
    public Pet findConditionalPet(final int pid) {
        try {
            return (DbManager
                    .readWithCondition(Pet.class,
                            TableSchema.Name.PET,
                            Map.of(TableSchema.Column.Id,
                                    String.valueOf(pid)))).getFirst();
        } catch (Exception ex) {
            System.out.println("Error during finding specific pet: "
                    + ex.getMessage());
        }
        return null;
    }

    /**
     * Finds all pets.
     *
     * @return a list of all pets, or null if an error occurs
     */
    public ArrayList<Pet> findAllPet() {
        try {
            return DbManager.readAll(Pet.class, "PET");
        } catch (Exception ex) {
            System.out.println("Error during finding all pets: "
                    + ex.getMessage());
        }
        return null;
    }

    /**
     * Checks the validity of the pet's state.
     *
     * @param thisPet the pet to check
     * @return 0 if the pet is null, 1 if the pet's state is valid, 2 otherwise
     */
    public int isValidPetState(final Pet thisPet) {
        if (thisPet == null) {
            return 0;
        } else if (thisPet.getState() == 1) {
            return 1;
        }
        return 2;
    }
}

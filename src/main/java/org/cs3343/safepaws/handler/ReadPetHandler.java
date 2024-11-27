package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;

import java.util.ArrayList;
import java.util.Map;

public class ReadPetHandler {

    private static ReadPetHandler instance;

    private ReadPetHandler() {
    }

    public static ReadPetHandler getInstance() {
        if (instance == null) {
            instance = new ReadPetHandler();
        }
        return instance;
    }

    public Pet findConditionalPet(final int pid) {
        try {
            return (DbManager.getInstance()
                    .readWithCondition(Pet.class,
                            "PET",
                            Map.of("Id", String.valueOf(pid)))).getFirst();
        } catch (Exception ex) {
            System.out.println("Error during finding specific pet: "
                    + ex.getMessage());
        }
        return null;
    }

    public ArrayList<Pet> findAllPet() {
        try {
            return DbManager.getInstance().readAll(Pet.class,
                    "PET");
        } catch (Exception ex) {
            System.out.println("Error during finding all pets: "
                    + ex.getMessage());
        }
        return null;
    }

    public int isValidPetState(final Pet thisPet) {
            if (thisPet == null) {
                return 0;
            } else if (thisPet.getState() == 1) {
                return 1;
            }
            return 2;
    }


}

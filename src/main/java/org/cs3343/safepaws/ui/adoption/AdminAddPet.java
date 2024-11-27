package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.PetRegisterHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

/**
 * The AdminAddPet class provides a UI for administrators to add a new pet to
 * the database.
 */
public final class AdminAddPet extends UI {

    /**
     * The name of the UI for adding a pet to the database.
     */
    private static final String NAME = "Add one pet to database";

    /**
     * The maximum value for certain numeric attributes.
     */
    private static final int MAX_VAL = 10;

    /**
     * Constructs an AdminAddPet instance.
     *
     * @param referrer the UI that referred to this UI
     */
    public AdminAddPet(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the process of adding a new pet to the database.
     *
     * @param session the current session
     * @return the UI to return to after execution
     */
    @Override
    protected UI execute(final Session session) {
        String name;
        String species;
        String breed;
        int age;
        int weight;
        String gender;
        int activityLevel;
        int healthStatus;

        session.println("Enter the name of this pet "
                + "(length: 0-30, any character):");
        name = session.requestInput();
        while (!Pet.isValidName(name)) {
            session.println("Your input name is invalid. "
                    + "Please enter again:");
            name = session.requestInput();
        }

        session.println("Enter the species of this pet "
                + "(length: 0-30, any character):");
        species = session.requestInput();
        while (!Pet.isValidSpecies(species)) {
            session.println("Your input species is invalid. "
                    + "Please enter again:");
            species = session.requestInput();
        }

        session.println("Enter the breed of this pet "
                + "(length: 0-30, any character):");
        breed = session.requestInput();
        while (!Pet.isValidBreed(breed)) {
            session.println("Your input breed is invalid. "
                    + "Please enter again:");
            breed = session.requestInput();
        }

        session.println("Enter the age of this pet "
                + "(a positive integer):");
        age = session.requestNumericInput(0, Integer.MAX_VALUE);

        session.println("Enter the weight of this pet "
                + "(a positive integer):");
        weight = session.requestNumericInput(0, Integer.MAX_VALUE);

        session.println("Enter the gender of this pet "
                + "(\"m\" for male, \"f\" for female):");
        gender = session.requestInput();
        while (!Pet.isValidGender(gender)) {
            session.println("Your input gender is invalid. "
                    + "Please enter again:");
            gender = session.requestInput();
        }

        session.println(
                "Enter the activity level of this pet "
                        + "(a positive integer meeting 0-" + MAX_VAL + ", "
                        + "larger numbers indicate more activity):");
        activityLevel = session.requestNumericInput(0, MAX_VAL);

        session.println(
                "Enter the health status of this pet (a positive "
                        + "integer meeting 0-" + MAX_VAL
                        + ", larger numbers indicate healthier):");
        healthStatus = session.requestNumericInput(0, MAX_VAL);

        int[] numericAttributes = {age, weight, activityLevel, healthStatus};

        Pet pet =
                new Pet(-1, name, species, breed, gender, numericAttributes, 0);
        try {
            PetRegisterHandler.getInstance().insertPetRecord(pet);
            session.println("Pet created successfully.");
        } catch (Exception e) {
            session.println("Error during admin adding pet.");
        }

        return this.getReferrer();
    }

    /**
     * Determines if this UI is visible to the current session.
     *
     * @param session the current session
     * @return true if the session's account is not null and has the role "A"
     * , false otherwise
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() instanceof Admin;
    }
}

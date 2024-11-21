package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;

public class AdminAddPet extends UI {
    private static final String NAME = "Admin Add one pet to database";

    public AdminAddPet(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) {
        String name;
        String species;
        String breed;
        int age;
        int weight;
        String gender;
        int activityLevel;
        int healthStatus;

        session.println("Enter the name of this pet (length: 0-30, any character):");
        name = session.requestInput();     
        while (!Pet.isValidName(name)) {
            session.println("Your input name is invalid. Please enter again:");
            name = session.requestInput();
        }

        session.println("Enter the species of this pet (length: 0-30, any character):");
        species = session.requestInput();     
        while (!Pet.isValidSpecies(species)) {
            session.println("Your input species is invalid. Please enter again:");
            species = session.requestInput();
        }

        session.println("Enter the breed of this pet (length: 0-30, any character):");
        breed = session.requestInput();
        while (!Pet.isValidBreed(breed)) {
            session.println("Your input breed is invalid. Please enter again:");
            breed = session.requestInput();
        }

        session.println("Enter the age of this pet (a positive integer):");
        age = Integer.parseInt(session.requestInput());
        while (!Pet.isValidAge(age)) {
            session.println("Your input age is invalid. Please enter again:");
            age = Integer.parseInt(session.requestInput());
        }

        session.println("Enter the weight of this pet (a positive integer):");
        weight = Integer.parseInt(session.requestInput());
        while (!Pet.isValidWeight(weight)) {
            session.println("Your input weight is invalid. Please enter again:");
            weight = Integer.parseInt(session.requestInput());
        }

        session.println("Enter the gender of this pet (\"m\" for male, \"f\" for female):");
        gender = session.requestInput();
        while (!Pet.isValidGender(gender)) {
            session.println("Your input gender is invalid. Please enter again:");
            gender = session.requestInput();
        }

        session.println("Enter the activity level of this pet (a positive integer meeting 0-10, larger numbers indicate more activity):");
        activityLevel = Integer.parseInt(session.requestInput());
        while (!Pet.isValidActivityLevel(activityLevel)) {
            session.println("Your input activity level is invalid. Please enter again:");
            activityLevel = Integer.parseInt(session.requestInput());
        }

        session.println("Enter the health status of this pet (a positive integer meeting 0-10, larger numbers indicate healthier):");
        healthStatus = Integer.parseInt(session.requestInput());
        while (!Pet.isValidHealthStatus(healthStatus)) {
            session.println("Your input health status is invalid. Please enter again:");
            healthStatus = Integer.parseInt(session.requestInput());
        }

        Pet pet = new Pet(name, species, breed, age, weight, gender,
                activityLevel, healthStatus);
        try {
            DbManager.insertPet(pet);
            session.println("Pet created successfully.");
        } catch (Exception e) {
            session.println("Error during admin adding pet.");
        }

        return this.getReferrer();
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(
                session.getAccount().getRole());
    }
}

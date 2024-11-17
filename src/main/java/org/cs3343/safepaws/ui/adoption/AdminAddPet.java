package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;
import java.sql.SQLException;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class AdminAddPet extends UI{
	private static final String NAME = "Admin Add one pet to database";

    public AdminAddPet(UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
    	String name;
    	String species;
    	String breed;
    	int age;
    	int weight;
    	String gender;
    	int activityLevel;
    	int healthStatus;
    	
    	session.println("Enter the name of this pet:");
    	name=session.requestInput();
    	
    	session.println("Enter the species of this pet:");
    	species=session.requestInput();
    	
    	session.println("Enter the breed of this pet:");
        breed = session.requestInput();
        
        session.println("Enter the age of this pet:");
        age = Integer.parseInt(session.requestInput());
        
        session.println("Enter the weight of this pet:");
        weight = Integer.parseInt(session.requestInput());
    	
        session.println("Enter the gender of this pet (m/f):");
    	gender=session.requestInput();
        
        session.println("Enter the activity level of this pet:");
    	activityLevel = Integer.parseInt(session.requestInput());
    	
        session.println("Enter the health status of this pet:");
        healthStatus = Integer.parseInt(session.requestInput());
        
        Pet pet = new Pet(name, species, breed, age, weight, gender, activityLevel, healthStatus);
        try {
            DbManager.insertPet(pet);
            session.println("Pet created successfully.");
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }
    	
        return this.getReferrer(); 
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
}

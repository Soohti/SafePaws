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
    	int activityLevel;
    	int age;
    	int size;
    	int healthStatus;
    	String breed;
    	String gender;
    	
    	session.println("Enter the activity level of this pet:");
    	
    	activityLevel = Integer.parseInt(session.requestInput());
    	


    	age = Integer.parseInt(session.requestInput());
    	
        
        session.println("Enter the size of this pet:");
        size = Integer.parseInt(session.requestInput());
    	
        session.println("Enter the health status of this pet:");
        healthStatus = Integer.parseInt(session.requestInput());
        
        session.println("Enter the breed of this pet:");
        breed = session.requestInput();
        
        session.println("Enter the gender of this pet:");
        gender = session.requestInput();
        
        Pet pet = new Pet(breed, age, size, gender, activityLevel, healthStatus);
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

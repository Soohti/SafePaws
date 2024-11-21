package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.DbManager;

/**
 * Represents an application with user, pet, state, and id.
 */
public class Application {
    /**
     * The user associated with the application.
     */
    private Member user;

    /**
     * The pet associated with the application.
     */
    private Pet pet;

    /**
     * The state of the application.
     */
    private int state;

    /**
     * The id of the application.
     */
    private int id;

    /**
     * Constructs an Application with the specified user, pet, and state.
     *
     * @param account the user account
     * @param p       the pet
     * @param st      the state
     */
    public Application(final Member account, final Pet p, final int st) {
        this.user = account;
        this.pet = p;
        this.state = st;
    }
    
    public static boolean isValidState(int state) {
        if (state==1||state==2) {
            return true;
        }
        return false;
    }
    
    public static boolean isValidAid(int Aid) {
        if(DbManager.selectApplication(Aid)==null||DbManager.selectApplication(Aid).state!=0) {
        	return false;
        }
        return true;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public Member getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user to set
     */
    public void setUser(final Member user) {
        this.user = user;
    }

    /**
     * Gets the pet.
     *
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Sets the pet.
     *
     * @param pet the pet to set
     */
    public void setPet(final Pet pet) {
        this.pet = pet;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the state to set
     */
    public void setState(final int state) {
        this.state = state;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }
}

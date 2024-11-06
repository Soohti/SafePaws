package org.cs3343.safepaws.entity;

public class Application {
	public Member user;
	public Pet pet;
	public int state;
	public int id;
	
	public Application(Member account, Pet pet, int state) {
        this.user = (Member) account;
        this.pet = pet;
        this.state = state;
    }
	
	public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setPet(Pet pet) {
        this.pet = pet;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

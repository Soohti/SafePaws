package org.cs3343.safepaws.entity;

public class Application {
	public Member user;
	public Pet pet;
	public int state;
	
	public Application(Member user, Pet pet, int state) {
        this.user = user;
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

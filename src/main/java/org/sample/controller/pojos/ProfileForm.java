package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;

/**
 * Created by zilti on 05.11.14.
 */
public class ProfileForm {

    @NotNull
    private String email;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    
    @NotNull
    private int age;
    
    @NotNull
    private char sex;
    
    private String description;

    private byte[] picture;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}

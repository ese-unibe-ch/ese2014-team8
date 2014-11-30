package org.sample.controller.pojos;

import org.sample.model.ApartmentTags;
import org.sample.model.User;
import javax.validation.constraints.*;


public class ApartmentForm extends RealEstateForm{
	
	//@Min(value=1, message="Please enter the number of rooms larger than 0.")
	private int numberOfRooms;
	private int size;
	
	private User user;
	
	private ApartmentTags tags;


	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ApartmentTags getTags() {
		return tags;
	}

	public void setTags(ApartmentTags tags) {
		this.tags = tags;
	}
}

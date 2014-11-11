package org.sample.controller.pojos;

import org.sample.model.User;

import java.util.Date;

import javax.validation.constraints.*;


public class ApartmentForm extends RealEstateForm{
	
	//@Min(value=1, message="Please enter the number of rooms larger than 0.")
	private int numberOfRooms;
	private int size;
	
	private User user;


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
}

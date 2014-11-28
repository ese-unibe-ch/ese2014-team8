package org.sample.controller.pojos;

import org.sample.model.User;

import javax.validation.constraints.Min;


public class ShApartmentForm extends RealEstateForm{

	//@Min(value=1, message="Please enter the number of rooms larger than 0.")
	private int roomSize;

	private User user;
	
	private boolean addRoomMate;
	

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAddRoomMate() {
		return addRoomMate;
	}

	public void setAddRoomMate(boolean addRoomMate) {
		this.addRoomMate = addRoomMate;
	}
}

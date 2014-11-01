package org.sample.controller.pojos;

import java.util.Date;

import javax.validation.constraints.*;


public class ApartmentForm extends RealEstateForm{
	
	@Min(value=1, message="Please enter the number of rooms larger than 0.")
	private int numberOfRooms;
	private int size;
	
	


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

	
	
}

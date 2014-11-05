package org.sample.model;

import javax.persistence.*;

@Entity
public class Apartment extends RealEstate {

	
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

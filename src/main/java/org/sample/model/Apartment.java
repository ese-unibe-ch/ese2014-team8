package org.sample.model;

import javax.persistence.*;

@Entity
public class Apartment extends RealEstate {

	
	private int numberOfRooms;
	private int size;
	@ManyToOne
	private User owner;
	

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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}

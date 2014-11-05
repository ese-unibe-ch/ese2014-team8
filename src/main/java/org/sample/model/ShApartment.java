package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
public class ShApartment extends RealEstate {

	private int roomSize;

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
}

package org.sample.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ShApartment extends RealEstate {

	private int roomSize;
	
	 @ManyToOne
	private User owner;

	@OneToMany(mappedBy = "shApartment", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<RoomMate> roomMates;	
	
	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}

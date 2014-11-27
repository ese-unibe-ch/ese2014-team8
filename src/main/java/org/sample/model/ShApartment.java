package org.sample.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ShApartment extends RealEstate {

	private int roomSize;

	@ManyToOne
	private User owner;
	
	@OneToMany(mappedBy = "shApartment")
	private Collection<TimeSlot> visitingTimes;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	public Collection<TimeSlot> getVisitingTimes() {
		return visitingTimes;
	}

	public void setVisitingTimes(Collection<TimeSlot> visitingTimes) {
		this.visitingTimes = visitingTimes;
	}
}

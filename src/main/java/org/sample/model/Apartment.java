package org.sample.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Apartment extends RealEstate {

	
	private int numberOfRooms;
	private int size;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@Column(name="visiting_times")
	@OneToMany(mappedBy = "apartment", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<TimeSlot> visitingTimes;
	
	@OneToMany(mappedBy = "ap", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<Message> messages;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

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

	public Collection<TimeSlot> getVisitingTimes() {
		return visitingTimes;
	}

	public void setVisitingTimes(Collection<TimeSlot> visitingTimes) {
		this.visitingTimes = visitingTimes;
	}
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

}

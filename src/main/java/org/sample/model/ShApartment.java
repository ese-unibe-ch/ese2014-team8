package org.sample.model;

import java.util.List;

import javax.persistence.CascadeType;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShApartment extends RealEstate {

	private int roomSize;


	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@Column(name="visiting_times")
	@OneToMany(mappedBy = "shApartment", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<TimeSlot> visitingTimes;
	
	@OneToMany(mappedBy = "shAp", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<Message> messages;
	
	@OneToMany(mappedBy = "shAp", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<BookMark> bookMarks;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private ShApartmentTags tags;
	
	@OneToMany(mappedBy = "shApartment", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<RoomMate> roomMates;	
	
	public ShApartmentTags getTags() {
		return tags;
	}

	public void setTags(ShApartmentTags tags) {
		this.tags = tags;
	}

	public List<RoomMate> getRoomMates() {
		return roomMates;
	}

	public void setRoomMates(List<RoomMate> roomMates) {
		this.roomMates = roomMates;
	}

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
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

}

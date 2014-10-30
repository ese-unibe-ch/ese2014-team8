package org.sample.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Apartment {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Address address;
	
	private int price;
	private boolean fixedMoveIn;
	
	@Type(type="date")
	private Date moveIn;
	
	
	private boolean fixedMoveOut;
	
	@Type(type="date")
	private Date moveOut;
	
	private int numberOfRooms;
	private int size;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public boolean isFixedMoveIn() {
		return fixedMoveIn;
	}

	public void setFixedMoveIn(boolean fixedMoveIn) {
		this.fixedMoveIn = fixedMoveIn;
	}

	public Date getMoveIn() {
		return moveIn;
	}

	public void setMoveIn(Date moveIn) {
		this.moveIn = moveIn;
	}

	public boolean isFixedMoveOut() {
		return fixedMoveOut;
	}

	public void setFixedMoveOut(boolean fixedMoveOut) {
		this.fixedMoveOut = fixedMoveOut;
	}

	public Date getMoveOut() {
		return moveOut;
	}

	public void setMoveOut(Date moveOut) {
		this.moveOut = moveOut;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

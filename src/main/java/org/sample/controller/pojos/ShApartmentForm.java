package org.sample.controller.pojos;

import java.util.Date;

public class ShApartmentForm {
	
	private long id;
	private String title;
	
	private String street;
	private int number;
	private String city;
	private int zipCode;
	
	private int price;
	private boolean fixedMoveIn;
	private Date moveIn;
	private boolean fixedMoveOut;
	private Date moveOut;
	
	private int numberOfRooms;
	private int size;
	private String description;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
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

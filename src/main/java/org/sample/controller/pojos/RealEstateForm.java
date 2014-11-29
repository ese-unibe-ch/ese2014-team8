package org.sample.controller.pojos;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.util.AutoPopulatingList;

public abstract class RealEstateForm {
	
	private long id;
	//@Size(min=5, message="Title cannot be empty")
	private String title;
	//@Size(min=5, max=100, message="Please write a valid street name.")
	private String street;
	//@Min(value=1, message="Please enter a number larger or equal to 1.")
	private int number;
	//@Size(min=3, message= "Please enter a city name.")
	private String city;
	//@Min(value=1000, message="Please enter a valid ZIP-code.")
	//@Max(value=9999, message="Please enter a valid ZIP-code.")
	private int zipCode;
	//@Min(value=1, message="Please enter a price larger than 0.")	
	private int price;
	private boolean fixedMoveIn;
	private Date moveIn;
	private boolean fixedMoveOut;
	private Date moveOut;
	
	private int distanceToPark;
	private int distanceToSchool;
	private int distanceToPubTr;
	private int distanceToShop;
	
	
	
	//@Size(min=3, message="Please enter a description.")
	private String description;
	
	private String category;
	

	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDistanceToPark() {
		return distanceToPark;
	}

	public void setDistanceToPark(int distanceToPark) {
		this.distanceToPark = distanceToPark;
	}

	public int getDistanceToSchool() {
		return distanceToSchool;
	}

	public void setDistanceToSchool(int distanceToSchool) {
		this.distanceToSchool = distanceToSchool;
	}

	public int getDistanceToPubTr() {
		return distanceToPubTr;
	}

	public void setDistanceToPubTr(int distanceToPubTr) {
		this.distanceToPubTr = distanceToPubTr;
	}

	public int getDistanceToShop() {
		return distanceToShop;
	}

	public void setDistanceToShop(int distanceToShop) {
		this.distanceToShop = distanceToShop;
	}

	
}

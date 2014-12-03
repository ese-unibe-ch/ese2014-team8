package org.sample.model;


import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public class RealEstate {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String title;
	@OneToOne(cascade = { CascadeType.ALL })
	private Address address;
	private int price; 
	private boolean fixedMoveIn;
	@Type(type = "date")
	private Date moveIn;
	private boolean fixedMoveOut;
	@Type(type = "date")
	private Date moveOut;
	private String description;
	
	private int distanceToPark;
	private int distanceToSchool;
	private int distanceToPubTr;
	private int distanceToShop;
	
	private int numberOfImages;

	public RealEstate() {
		super();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getNumberOfImages() {
		return numberOfImages;
	}

	public void setNumberOfImages(int numberOfImages) {
		this.numberOfImages = numberOfImages;
	}

}	

	
package org.sample.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class TimeSlot {
	
	 @Id
	 @GeneratedValue
	 private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	private int maxNumVisitors;
	private int placesLeft;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Collection<User> visitors;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "ap_id")
	private Apartment apartment;
	
	@ManyToOne
	@JoinColumn(name = "shAp_id")
	private ShApartment shApartment;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getMaxNumVisitors() {
		return maxNumVisitors;
	}

	public void setMaxNumVisitors(int maxNumVisitors) {
		this.maxNumVisitors = maxNumVisitors;
	}

	public int getPlacesLeft() {
		return placesLeft;
	}

	public void setPlacesLeft(int placesLeft) {
		this.placesLeft = placesLeft;
	}

	public Collection<User> getVisitors() {
		return visitors;
	}

	public void setVisitors(Collection<User> visitors) {
		this.visitors = visitors;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public ShApartment getShApartment() {
		return shApartment;
	}

	public void setShApartment(ShApartment shApartment) {
		this.shApartment = shApartment;
	}
	
}

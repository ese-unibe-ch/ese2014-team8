package org.sample.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
public class TimeSlot {
	
	 @Id
	 @GeneratedValue
	 private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	private int maxNumVisitors;
	
	@OneToMany
	private Collection<User> visitors;
	
	@ManyToOne
	private Apartment apartment;
	
	@ManyToOne
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

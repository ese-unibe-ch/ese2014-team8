package org.sample.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookMark {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "bookMarker_id")
	private User bookMarker;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "ap_id")
	private Apartment ap;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "shAp_id")
	private ShApartment shAp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getBookMarker() {
		return bookMarker;
	}

	public void setBookMarker(User bookMarker) {
		this.bookMarker = bookMarker;
	}

	public Apartment getAp() {
		return ap;
	}

	public void setAp(Apartment ap) {
		this.ap = ap;
	}

	public ShApartment getShAp() {
		return shAp;
	}

	public void setShAp(ShApartment shAp) {
		this.shAp = shAp;
	}
	
	
}

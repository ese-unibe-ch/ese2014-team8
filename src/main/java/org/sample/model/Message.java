package org.sample.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Message {
	
	 @Id
	 @GeneratedValue
	 private Long id;

	@ManyToOne
	private User sender;
	@ManyToOne
	private User receiver;
	
	private String subject;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	private boolean read;
	
	@ManyToOne
	private Apartment ap;
	
	@ManyToOne
	private ShApartment shAp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String message) {
		this.content = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
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

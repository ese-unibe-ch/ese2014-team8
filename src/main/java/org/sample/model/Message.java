package org.sample.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Message {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "sender_id")
	private User sender;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "receiver_id")
	private User receiver;
	
	private String subject;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	private boolean messageRead;
	
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

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date timestamp) {
		this.dateTime = timestamp;
	}

	public boolean isMessageRead() {
		return messageRead;
	}

	public void setMessageRead(boolean read) {
		this.messageRead = read;
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

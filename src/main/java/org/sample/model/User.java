package org.sample.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;


@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastMainHit;

    @OneToOne(cascade = {CascadeType.ALL})
    private Person person;
    
    private String email;
    @ElementCollection(targetClass = Team8Authority.class)
    private Collection<Team8Authority> authorities;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @Lob
    private byte[] picture;

    @OneToMany(mappedBy = "owner", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Apartment> apartments;
    
    @OneToMany(mappedBy = "owner", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<ShApartment> shApartments;

    private Boolean isNew;
    private Boolean isAdmin = true;
    
    public Collection<BookMark> getBookMarks() {
		return bookMarks;
	}

	public void setBookMarks(Collection<BookMark> bookMarks) {
		this.bookMarks = bookMarks;
	}

	@OneToMany(mappedBy ="bookMarker", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<BookMark> bookMarks;
    
     @OneToMany(mappedBy ="sender", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
     private Collection<Message> sentMessages;
     
     @OneToMany(mappedBy ="receiver", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
     private Collection<Message> receivedMessages;
     
     @ManyToMany(mappedBy = "visitors", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
     @Fetch(FetchMode.SELECT)
     private Collection<TimeSlot> registeredTimeSlots;
     
     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getFirstName() {
        return person.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.person.setFirstName(firstName);
    }

    public String getLastName() {
        return person.getLastName();
    }

    public void setLastName(String lastName) {
        this.person.setLastName(lastName);
    }
    
    public int getAge(){
    	return person.getAge();
    }
    
    public void setAge(int age){
    	this.person.setAge(age);
    }
    
    public char getSex(){
    	return person.getSex();
    }
    
    public void setSex(char sex){
    	this.person.setSex(sex);
    }
    
    public String getDescription(){
    	return person.getDescription();
    }
    
    public void setDescription(String description){
    	this.person.setDescription(description);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    public Collection<Team8Authority> getAuthorities() {return authorities;}
    public void setAuthorities(Collection<Team8Authority> authorities) {this.authorities = authorities;}

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Collection<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Collection<Apartment> apartments) {
        this.apartments = apartments;
    }

    public Collection<ShApartment> getShApartments() {
        return shApartments;
    }

    public void setShApartments(Collection<ShApartment> shApartments) {
        this.shApartments = shApartments;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

	public Collection<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Collection<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Collection<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(Collection<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

	public Collection<TimeSlot> getTimeSlots() {
		return registeredTimeSlots;
	}

	public void setTimeSlots(Collection<TimeSlot> timeSlots) {
		this.registeredTimeSlots = timeSlots;
	}

	public Date getLastMainHit() {
		return lastMainHit;
	}

	public void setLastMainHit(Date lastMainHit) {
		this.lastMainHit = lastMainHit;
	}

	public Collection<TimeSlot> getRegisteredTimeSlots() {
		return registeredTimeSlots;
	}

	public void setRegisteredTimeSlots(Collection<TimeSlot> registeredTimeSlots) {
		this.registeredTimeSlots = registeredTimeSlots;
	}

}

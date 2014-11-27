package org.sample.model;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
//import java.util.Arrays;
import java.util.Collection;


@Entity
public class RoomMate {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Person person;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    
	@ManyToOne
	@JoinColumn(name = "shApartment_id")
	private ShApartment shApartment;
    
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
    
    public ShApartment getShApartment() {
        return shApartment;
    }

    public void setShApartment(ShApartment shApartment) {
        this.shApartment = shApartment;
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

/*    public Collection<Team8Authority> getAuthorities() {return authorities;}
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
}*/

package org.sample.model;


import javax.persistence.*;


@Entity
public class RoomMate {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Person person;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    
	@ManyToOne(cascade = CascadeType.REFRESH)
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


package org.sample.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;


@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    @ElementCollection(targetClass = Team8Authority.class)
    private Collection<Team8Authority> authorities;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @OneToMany
    private Collection<Apartment> apartments;
    @OneToMany
    private Collection<ShApartment> shApartments;

    private Boolean isNew;
    private Boolean isAdmin;

    @ManyToOne
    private Team team;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Team getTeam() {return team;}
    public void setTeam(Team team) {this.team = team;}

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
}

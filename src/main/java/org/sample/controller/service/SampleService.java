package org.sample.controller.service;

import java.util.List;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ProfileForm;
import org.sample.controller.pojos.ApartmentForm;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.pojos.ShApartmentForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.model.Apartment;
import org.sample.model.RealEstate;
import org.sample.model.ShApartment;
import org.sample.model.Team;
import org.sample.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;

    public User getUser(Long id);

    public User loadUserByEmail(String email);

    public TeamCreationForm saveTeamFrom(TeamCreationForm teamCreationForm) throws Exception;

    public Iterable<Team> getAllTeams();

	public Apartment saveFrom(ApartmentForm adForm);
	public ShApartment saveFrom(ShApartmentForm form2);

	public Apartment getAd(long id); //mg
	public ShApartment getShApAd(long id);

	public Iterable<Apartment> getSearchResults(SearchForm searchForm);
	
	public List<String> getCategories();


    public ProfileForm saveFrom(ProfileForm profileForm);

}

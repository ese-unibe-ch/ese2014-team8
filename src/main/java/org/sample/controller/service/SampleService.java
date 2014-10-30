package org.sample.controller.service;

import java.util.List;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.model.Ad;
import org.sample.model.Team;
import org.sample.model.User;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;

    public User getUser(Long id);

    public TeamCreationForm saveTeamFrom(TeamCreationForm teamCreationForm) throws Exception;

    public Iterable<Team> getAllTeams();

	public AdForm saveFrom(AdForm adForm);

	public Ad getAd(long id);

	public Iterable<Ad> getSearchResults(SearchForm searchForm);
	
	public List<String> getCategories();

}

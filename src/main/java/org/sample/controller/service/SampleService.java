package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.ProfileForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
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

	public AdForm saveFrom(AdForm adForm);

    public ProfileForm saveFrom(ProfileForm profileForm);

}

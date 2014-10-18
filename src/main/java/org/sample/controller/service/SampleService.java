package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.model.Team;
import org.sample.model.User;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;

    public User getUser(Long id);

    public TeamCreationForm saveTeamFrom(TeamCreationForm teamCreationForm) throws Exception;

    public Iterable<Team> getAllTeams();

}

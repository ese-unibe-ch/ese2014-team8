package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.model.Address;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    @Autowired    TeamDao teamDao;
    
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }


        Address address = new Address();
        address.setStreet("TestStreet-foo");
        
        User user = new User();
        user.setFirstName(signupForm.getFirstName());
        user.setEmail(signupForm.getEmail());
        user.setLastName(signupForm.getLastName());
        user.setAddress(address);

        Long teamId = Long.parseLong(signupForm.getTeam());
        Team team = null;

        if(teamId != -1) {
            team = teamDao.findOne(teamId);
            teamDao.save(team);
        }
        user.setTeam(team);
        user = userDao.save(user);   // save object to DB
        
        // Iterable<Address> addresses = addDao.findAll();  // find all 
        // Address anAddress = addDao.findOne((long)3); // find by ID
        
        
        signupForm.setId(user.getId());

        return signupForm;

    }

    public User getUser(Long id) {return userDao.findOne(id);}

    public Iterable<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @Transactional
    public TeamCreationForm saveTeamFrom(TeamCreationForm teamCreationForm) throws Exception {
        String teamname = teamCreationForm.getTeamname();

        Team team = new Team();
        team.setTeamname(teamname);
        team.setTimestamp(new Timestamp(System.currentTimeMillis()));

        team = teamDao.save(team);

        teamCreationForm.setId(team.getId());
        teamCreationForm.setTimestamp(team.getTimestamp());

        return teamCreationForm;
    }
}

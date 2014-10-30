package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ApartmentForm;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.model.Apartment;
import org.sample.model.Address;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    @Autowired    TeamDao teamDao;
	@Autowired	  ApartmentDao apDao;
    
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

    public User getUser(Long id) {
    	return userDao.findOne(id);
    }

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

    @Transactional
	public ApartmentForm saveFrom(ApartmentForm apartmentForm) {
		Apartment apartment;
		Address address;
		
    	if(apartmentForm.getId()!=0L){
    		apartment = getAd(apartmentForm.getId());
    		address = apartment.getAddress();
    	}
    	else{
    		address = new Address();
    		apartment = new Apartment();
    	}
    	
    	address.setStreet(apartmentForm.getStreet());
    	address.setNumber(apartmentForm.getNumber());
    	address.setCity(apartmentForm.getCity());
    	address.setZipCode(apartmentForm.getZipCode());	
    	
    	apartment.setAddress(address);
    	apartment.setNumberOfRooms(apartmentForm.getNumberOfRooms());
    	apartment.setPrice(apartmentForm.getPrice());
    	apartment.setFixedMoveIn(apartmentForm.isFixedMoveIn());
    	if(apartment.isFixedMoveIn()){
    		apartment.setMoveIn(apartmentForm.getMoveIn());
    	}
    	apartment.setFixedMoveOut(apartmentForm.isFixedMoveOut());
    	if(apartment.isFixedMoveOut()){
    		apartment.setMoveOut(apartmentForm.getMoveOut());
    	}
    	apartment.setSize(apartmentForm.getSize());
    	apartment.setTitle(apartmentForm.getTitle());
    	apartment.setDescription(apartmentForm.getDescription());
    	
    	
    	apartment = apDao.save(apartment);
    	
    	apartmentForm.setId(apartment.getId());
    	return apartmentForm;
		
		
	}

    @Transactional
	public Apartment getAd(long id) {
		return apDao.findOne(id);
	}

    @Transactional
	public Iterable<Apartment> getSearchResults(SearchForm searchForm) {
		return apDao.findAll();
		
	}

    @Transactional
	public List<String> getCategories() {
		List<String> categories = new ArrayList<String>();
		categories.add("Apartment");
		categories.add("Shared Apartment");
		return categories;
	}
}

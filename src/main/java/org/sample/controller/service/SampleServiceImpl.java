package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.ShApartmentDao;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    @Autowired    TeamDao teamDao;
	@Autowired	  ApartmentDao apDao;
	@Autowired	  ShApartmentDao shApDao;
	
	public SampleServiceImpl() {
    }
	
	@Autowired
	public SampleServiceImpl(ApartmentDao apDao, ShApartmentDao shApDao){
		this.apDao = apDao;
		this.shApDao = shApDao;
	}
    
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

    public User loadUserByEmail(String email) {return userDao.findByEmail(email);}

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

 /*   @Transactional
	public AdForm saveFrom(AdForm adForm) {

        Address address = new Address();
        address.setStreet(adForm.getStreet());
        address.setNumber(adForm.getNumber());
        address.setCity(adForm.getCity());
        address.setZipCode(adForm.getZipCode());


        Ad ad = new Ad();
        ad.setAddress(address);
        ad.setNumberOfRooms(adForm.getNumberOfRooms());
        ad.setPrice(adForm.getPrice());
        ad.setTitle(adForm.getTitle());
        ad.setDescription(adForm.getDescription());

        ad = adDao.save(ad);

        adForm.setId(ad.getId());
        return adForm;

    }*/

    @Override
    public ProfileForm saveFrom(ProfileForm profileForm) {
        User user = loadUserByEmail(profileForm.getEmail());
        user.setFirstName(profileForm.getFirstName());
        user.setLastName(profileForm.getLastName());
        userDao.save(user);
        return profileForm;
    }

	public NewProfileForm saveFrom(NewProfileForm newProfileForm) {
		User user = loadUserByEmail(newProfileForm.getEmail());
		user.setFirstName(newProfileForm.getFirstName());
		user.setLastName(newProfileForm.getLastName());
		user.setIsNew(false);
		userDao.save(user);
		return newProfileForm;
	}

	public Apartment saveFrom(ApartmentForm apartmentForm)throws InvalidDateException {
		
    	checkDates(apartmentForm);
    	
    	Apartment apartment;
		
		
    	if(apartmentForm.getId()!=0L){
    		apartment = getAd(apartmentForm.getId());
    	}
    	else{
    		apartment = new Apartment();
    	}
    	
    	apartment=(Apartment) setRealEstateFields(apartmentForm, apartment);
    	apartment.setNumberOfRooms(apartmentForm.getNumberOfRooms());
    	apartment.setSize(apartmentForm.getSize());
		apartment.setOwner(apartmentForm.getUser());
    	
    	
    	apartment = apDao.save(apartment);
    	
    	apartmentForm.setId(apartment.getId());
    	return apartment;
		
		
	}
    
    @Transactional
    public ShApartment saveFrom(ShApartmentForm form) {
    	checkDates(form);
    	ShApartment apartment;
		
		
    	if(form.getId()!=0L){
    		apartment =shApDao.findOne(form.getId());
    	}
    	else{
    		apartment = new ShApartment();
    	}
    	
    	apartment=(ShApartment) setRealEstateFields(form, apartment);
    	apartment.setRoomSize(form.getRoomSize());
		apartment.setOwner(form.getUser());
    	
    	
    	System.out.println("shared apartment before persistance");
    	apartment = shApDao.save(apartment);
    	System.out.println(apartment.getId());
    	form.setId(apartment.getId());
    	return apartment;
		
	}

	private RealEstate setRealEstateFields(RealEstateForm form, RealEstate ad) {
		
		Address address;
		
		address=new Address();
		address.setStreet(form.getStreet());
    	address.setNumber(form.getNumber());
    	address.setCity(form.getCity());
    	address.setZipCode(form.getZipCode());	
    	
    	ad.setTitle(form.getTitle());
    	ad.setAddress(address);
    	ad.setFixedMoveIn(form.isFixedMoveIn());
    	if(ad.isFixedMoveIn()){
    		ad.setMoveIn(form.getMoveIn());
    	}
    	ad.setFixedMoveOut(form.isFixedMoveOut());
    	if(ad.isFixedMoveOut()){
    		ad.setMoveOut(form.getMoveOut());
    	}
    	ad.setPrice(form.getPrice());
    	ad.setDescription(form.getDescription());
    	return ad;
	}

	private void checkDates(RealEstateForm form)
			throws InvalidDateException {
		if(form.isFixedMoveIn()&&!isFutureDate(form.getMoveIn())){
    		throw new InvalidDateException("Move-in date is not valid!"); 
    	}
    	
    	if(form.isFixedMoveOut()&&!isFutureDate(form.getMoveOut())){
    		throw new InvalidDateException("Move-out date is not valid!"); 	
    	}
    	
    	if(form.isFixedMoveIn()&&
    			form.isFixedMoveOut()&&
    			!form.getMoveOut().after(form.getMoveIn())){
    			throw new InvalidDateException("Move-out date must be later than move-in date!");
    	}
	}
   

	private boolean isFutureDate(Date moveIn) {
    	java.util.Date now = new java.util.Date();
    	return moveIn.after(now);
	}


    @Transactional
	public Iterable<? extends RealEstate> getSearchResults(SearchForm searchForm) {
		if(searchForm.getCategory().equals("Apartment")){
			return apDao.findByAddressZipCode(searchForm.getZipCode());
		}
		return shApDao.findByAddressZipCode(searchForm.getZipCode());
	}

	@Transactional
	public Collection<Apartment> getApartmentsByUser(String email) {
		return apDao.findByOwner(userDao.findByEmail(email));
	}

	@Transactional
	public Collection<ShApartment> getShApartmentsByUser(String email) {
		return shApDao.findByOwner(userDao.findByEmail(email));
	}

    @Transactional
	public List<String> getCategories() {
		List<String> categories = new ArrayList<String>();
		categories.add("Apartment");
		categories.add("Shared Apartment");
		return categories;
	}
    
	@Transactional
	public Apartment getAd(long id) {
		return apDao.findOne(id);
	}
	
    @Transactional
	public ShApartment getShApAd(long id) {
		return shApDao.findOne(id);
	}


	public ApartmentForm saveFrom(Apartment apartment) {

		ApartmentForm apartmentForm = new ApartmentForm();
    	
		apartmentForm = (ApartmentForm) saveFrom(apartment, apartmentForm);
		
    	apartmentForm.setNumberOfRooms(apartment.getNumberOfRooms());
    	apartmentForm.setSize(apartment.getSize());
    	
    	return apartmentForm;
	}

	public ShApartmentForm saveFrom(ShApartment shApartment) {
		ShApartmentForm shApartmentForm = new ShApartmentForm();
    	
		shApartmentForm = (ShApartmentForm) saveFrom(shApartment, shApartmentForm);
		
    	shApartmentForm.setRoomSize(shApartment.getRoomSize());
    	
    	return shApartmentForm;
	}
	
	private RealEstateForm saveFrom(RealEstate realEstate, RealEstateForm realEstateForm){
		Address address = realEstate.getAddress();
		
		realEstateForm.setId(realEstate.getId());
		
		// getAddress
    	realEstateForm.setStreet(address.getStreet());
    	realEstateForm.setNumber(address.getNumber());
    	realEstateForm.setCity(address.getCity());
    	realEstateForm.setZipCode(address.getZipCode());
    	
    	realEstateForm.setPrice(realEstate.getPrice());
    	realEstateForm.setMoveIn(realEstate.getMoveIn());
    	realEstateForm.setMoveOut(realEstate.getMoveOut());
    	realEstateForm.setDescription(realEstate.getDescription());
    	
		return realEstateForm;
	}
	
}

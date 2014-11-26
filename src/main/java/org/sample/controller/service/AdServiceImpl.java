package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.ShApartmentDao;
import org.sample.model.dao.TimeSlotDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;


@Service
public class AdServiceImpl implements AdService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
	@Autowired	  ApartmentDao apDao;
	@Autowired	  ShApartmentDao shApDao;
	@Autowired	TimeSlotDao timeSlotDao;
	
	public AdServiceImpl() {
    }
	
	@Autowired
	public AdServiceImpl(ApartmentDao apDao, ShApartmentDao shApDao){
		this.apDao = apDao;
		this.shApDao = shApDao;
	}
    
    
	public Apartment saveFrom(ApartmentForm apartmentForm)throws InvalidDateException {
		
    	checkDates(apartmentForm);
    	
    	Apartment apartment;
		
		
    	if(apartmentForm.getId()!=0L){
    		apartment = getApAd(apartmentForm.getId());
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
    	
    	apartment = shApDao.save(apartment);
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


	/**Finds the matching elements in the appropriate database via the autowired dao. 
	 * @param searchForm a searchForm containing the searchcriteria
	 * @return an Iterable of RealEstates (Apartments or Shared apartments depending on the search criteria in searchForm)
	 */
    @Transactional
	public Iterable<? extends RealEstate> getSearchResults(SearchForm searchForm) {
		if(searchForm.getCategory().equals("Apartment")){
			return apDao.findByAddressZipCode(searchForm.getZipCode());
		}
		return shApDao.findByAddressZipCode(searchForm.getZipCode());
	}

	@Transactional
	public Iterable<Apartment> getApartments() {
		return apDao.findAll();
	}

	@Transactional
	public Collection<Apartment> getApartmentsByUser(String email) {
		return apDao.findByOwner(userDao.findByEmail(email));
	}

	@Transactional
	public Iterable<ShApartment> getShApartments() {
		return shApDao.findAll();
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
    
    @Override
	public RealEstate getAd(String category, Long AdId) {
		RealEstate ad;
		if(category.equals("Apartment")){
			ad = getApAd(AdId);
		}
		else{
			ad=getShApAd(AdId);
		}
		return ad;
	}
    
	@Transactional
	public Apartment getApAd(long id) {
		Apartment apartment = apDao.findOne(id);
		apartment.setVisitingTimes(timeSlotDao.findByApartment(apartment));
		return apartment;
		
	}
	
    @Transactional
	public ShApartment getShApAd(long id) {
		ShApartment shApartment = shApDao.findOne(id);
		shApartment.setVisitingTimes(timeSlotDao.findByShApartment(shApartment));
    	return shApartment;
	}


	public ApartmentForm fillInFormFrom(Apartment apartment) {

		ApartmentForm apartmentForm = new ApartmentForm();
    	
		apartmentForm = (ApartmentForm) fillInFormFrom(apartment, apartmentForm);
		
    	apartmentForm.setNumberOfRooms(apartment.getNumberOfRooms());
    	apartmentForm.setSize(apartment.getSize());
    	
    	return apartmentForm;
	}

	public ShApartmentForm fillInFormFrom(ShApartment shApartment) {
		ShApartmentForm shApartmentForm = new ShApartmentForm();
    	
		shApartmentForm = (ShApartmentForm) fillInFormFrom(shApartment, shApartmentForm);
		
    	shApartmentForm.setRoomSize(shApartment.getRoomSize());
    	
    	return shApartmentForm;
	}
	
	private RealEstateForm fillInFormFrom(RealEstate realEstate, RealEstateForm realEstateForm){
		Address address = realEstate.getAddress();
		
		realEstateForm.setId(realEstate.getId());
		realEstateForm.setTitle(realEstate.getTitle());
		
		// getAddress
    	realEstateForm.setStreet(address.getStreet());
    	realEstateForm.setNumber(address.getNumber());
    	realEstateForm.setCity(address.getCity());
    	realEstateForm.setZipCode(address.getZipCode());
    	
    	realEstateForm.setPrice(realEstate.getPrice());
    	realEstateForm.setMoveIn(realEstate.getMoveIn());
    	realEstateForm.setFixedMoveIn(realEstate.isFixedMoveIn());
    	realEstateForm.setMoveOut(realEstate.getMoveOut());
    	realEstateForm.setFixedMoveOut(realEstate.isFixedMoveOut());
    	realEstateForm.setDescription(realEstate.getDescription());
    	
		return realEstateForm;
	}

	@Override
	public Collection<TimeSlot> addTimeSlot(TimeSlotForm timeSlotForm) {
		TimeSlot timeSlot = new TimeSlot();
		Date dateTime = new Date(timeSlotForm.getDate().getTime()+timeSlotForm.getTime().getTime()+(60*60*1000));
		System.out.println(dateTime);
		timeSlot.setDateTime(dateTime);
		timeSlot.setMaxNumVisitors(timeSlotForm.getMaxNumVisitors());
		
		
		if(timeSlotForm.getCategory().equals("Apartment")){
			timeSlot.setApartment(apDao.findOne(timeSlotForm.getAdId()));
			timeSlotDao.save(timeSlot);
		}
		else if(timeSlotForm.getCategory().equals("Shared Apartment")){
			timeSlot.setShApartment(shApDao.findOne(timeSlotForm.getAdId()));
			timeSlotDao.save(timeSlot);	
		}
		
		return getTimeSlots(timeSlotForm.getCategory(), timeSlotForm.getAdId());
	}

	@Override
	public void deleteTimeSlot(long id) {
		timeSlotDao.delete(id);
		
	}

	@Override
	public Collection<TimeSlot> getTimeSlots(String adCategory, long adId) {
		Collection<TimeSlot> timeSlots = Collections.emptySet();
		if(adCategory.equals("Apartment")){
			timeSlots = timeSlotDao.findByApartment(apDao.findOne(adId));
		}
		else if(adCategory.equals("Shared Apartment")){
			timeSlots = timeSlotDao.findByShApartment(shApDao.findOne(adId));
		}
		
		
		return timeSlots;
	}

	
	
}

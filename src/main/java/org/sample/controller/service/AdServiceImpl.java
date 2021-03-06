package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidDateException;
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
import sun.security.provider.SHA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;


@Service
public class AdServiceImpl implements AdService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
	@Autowired	  ApartmentDao apDao;
	@Autowired	  ShApartmentDao shApDao;
	@Autowired	TimeSlotDao timeSlotDao;

	@PersistenceContext
	private EntityManager em;
	
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
    	apartment.setTags(apartmentForm.getTags());
    	
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
		apartment.setTags(form.getTags());
    	
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
    	
    	ad.setDistanceToPark(form.getDistanceToPark());
    	ad.setDistanceToPubTr(form.getDistanceToPubTr());
    	ad.setDistanceToSchool(form.getDistanceToSchool());
    	ad.setDistanceToShop(form.getDistanceToShop());
    	
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
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<? extends RealEstate> query = null;
		Root<? extends RealEstate> root = null;
		
		if(searchForm.getCategory().equals("Apartment")){
			query = builder.createQuery(Apartment.class);
			root = query.from(Apartment.class);
		} else {
			query = builder.createQuery(ShApartment.class);
			root = query.from(ShApartment.class);
		}

		ArrayList<Predicate> predicates = new ArrayList<>();

		if(searchForm.getZipCode() != null) {
			predicates.add(
					builder.equal(root.<Address>get("address").get("zipCode"), searchForm.getZipCode()));
		}
		if(searchForm.getCity() != null && !searchForm.getCity().isEmpty()) {
			predicates.add(
					builder.equal(root.<Address>get("address").get("city"), searchForm.getCity()));
		}
		if(searchForm.getMinPrice() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.<Integer>get("price"), searchForm.getMinPrice()));
		}
		if(searchForm.getMaxPrice() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.<Integer>get("price"), searchForm.getMaxPrice()));
		}
		query.where(builder.and(predicates.toArray(new Predicate[]{})));
		return em.createQuery(query).getResultList();
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
    	apartmentForm.setTags(apartment.getTags());
    	
    	return apartmentForm;
	}

	public ShApartmentForm fillInFormFrom(ShApartment shApartment) {
		ShApartmentForm shApartmentForm = new ShApartmentForm();
    	
		shApartmentForm = (ShApartmentForm) fillInFormFrom(shApartment, shApartmentForm);
		
    	shApartmentForm.setRoomSize(shApartment.getRoomSize());
    	shApartmentForm.setTags(shApartment.getTags());
    	
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
    	
    	realEstateForm.setDistanceToPark(realEstate.getDistanceToPark());
    	realEstateForm.setDistanceToPubTr(realEstate.getDistanceToPubTr());
    	realEstateForm.setDistanceToSchool(realEstate.getDistanceToSchool());
    	realEstateForm.setDistanceToShop(realEstate.getDistanceToShop());
    	
    	realEstateForm.setPrice(realEstate.getPrice());
		realEstateForm.setMoveIn(realEstate.getMoveIn());
		realEstateForm.setFixedMoveIn(realEstate.isFixedMoveIn());
		realEstateForm.setMoveOut(realEstate.getMoveOut());
		realEstateForm.setFixedMoveOut(realEstate.isFixedMoveOut());
		realEstateForm.setDescription(realEstate.getDescription());
		realEstateForm.setUploadedImages(realEstate.getNumberOfImages());
    	
		return realEstateForm;
	}

	@Override
	public void deleteAd(String category, Long adId) {
		if(category.equals("Apartment")){
			apDao.delete(adId);
		}
		else{
			shApDao.delete(adId);
		}
		
	}

	@Override
	public Apartment setImages(Apartment apartment, int number) {
		apartment.setNumberOfImages(number);
		apDao.save(apartment);
		return apartment;
	}
	
	@Override
	public ShApartment setImages(ShApartment apartment, int number) {
		apartment.setNumberOfImages(number);
		shApDao.save(apartment);
		return apartment;
	}
}

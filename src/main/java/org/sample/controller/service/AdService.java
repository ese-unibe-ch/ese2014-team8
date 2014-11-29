package org.sample.controller.service;

import java.util.Collection;
import java.util.List;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;

public interface AdService {

        
	public Apartment saveFrom(ApartmentForm adForm);
	public ShApartment saveFrom(ShApartmentForm form2);
	
	public ApartmentForm fillInFormFrom(Apartment apartment);
	public ShApartmentForm fillInFormFrom(ShApartment shApartment);
	
	public RealEstate getAd(String category, Long lAdId);
	public Apartment getApAd(long id);
	public ShApartment getShApAd(long id);

	public Iterable<? extends RealEstate> getSearchResults(SearchForm searchForm);

	public Iterable<Apartment> getApartments();

    public Collection<Apartment> getApartmentsByUser(String email);

	public Iterable<ShApartment> getShApartments();

    public Collection<ShApartment> getShApartmentsByUser(String email);
	
	public List<String> getCategories();
	
	public void deleteAd(String category, Long adId);
	

}

package org.sample.model.dao;

import java.util.Collection;
import java.util.List;

import org.sample.model.Apartment;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;


public interface ApartmentDao extends CrudRepository<Apartment,Long> {

	List<Apartment> findByAddressZipCode(int zipCode);
	Collection<Apartment> findByOwner(User owner);
}

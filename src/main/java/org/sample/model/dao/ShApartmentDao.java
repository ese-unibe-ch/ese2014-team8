package org.sample.model.dao;


import java.util.Collection;
import java.util.List;

import org.sample.model.Apartment;
import org.sample.model.ShApartment;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;


public interface ShApartmentDao extends CrudRepository<ShApartment,Long> {

	List<ShApartment> findByAddressZipCode(int zipCode);
	Collection<ShApartment> findByOwner(User owner);

}

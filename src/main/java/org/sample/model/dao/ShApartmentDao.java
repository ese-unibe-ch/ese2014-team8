package org.sample.model.dao;


import java.util.List;

import org.sample.model.Apartment;
import org.sample.model.ShApartment;
import org.springframework.data.repository.CrudRepository;


public interface ShApartmentDao extends CrudRepository<ShApartment,Long> {

	List<Apartment> findByAddressZipCode(int zipCode);

}

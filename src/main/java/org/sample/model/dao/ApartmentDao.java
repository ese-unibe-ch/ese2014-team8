package org.sample.model.dao;

import org.sample.model.Apartment;
import org.springframework.data.repository.CrudRepository;


public interface ApartmentDao extends CrudRepository<Apartment,Long> {

}

package org.sample.model.dao;

import org.sample.model.Apartment;
import org.sample.model.BookMark;
import org.sample.model.ShApartment;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface BookMarkDao extends CrudRepository<BookMark,Long>{

	Iterable<BookMark> findByShApAndBookMarker(ShApartment shAp, User bookMarker);
	Iterable<BookMark> findByApAndBookMarker(Apartment ap, User bookMarker);
	//Iterable<BookMark> findByUser(User bookMarker);
}

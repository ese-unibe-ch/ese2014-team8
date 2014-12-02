package org.sample.model.dao;

import org.sample.model.Apartment;
import org.sample.model.BookMark;
import org.sample.model.ShApartment;
import org.springframework.data.repository.CrudRepository;

public interface BookMarkDao extends CrudRepository<BookMark,Long>{

	Iterable<BookMark> findByShAp(ShApartment shAp);
	Iterable<BookMark> findByAp(Apartment ap);
}

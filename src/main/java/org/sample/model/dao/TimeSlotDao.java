package org.sample.model.dao;

import java.util.Collection;

import org.sample.model.Apartment;
import org.sample.model.ShApartment;
import org.sample.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;

public interface TimeSlotDao extends CrudRepository<TimeSlot,Long> {
	Collection<TimeSlot> findByApartment(Apartment ap);
	Collection<TimeSlot> findByShApartment(ShApartment shAp);

}

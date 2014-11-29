package org.sample.model.dao;

import org.sample.model.RoomMate;
import org.sample.model.ShApartment;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RoomMateDao extends CrudRepository<RoomMate,Long> {
	Iterable<RoomMate> findByShApartment(ShApartment shAp);
    
}

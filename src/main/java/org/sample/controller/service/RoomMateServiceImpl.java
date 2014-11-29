package org.sample.controller.service;

import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.RoomMateDao;
import org.sample.model.dao.ShApartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;


@Service
public class RoomMateServiceImpl implements RoomMateService {

    @Autowired	RoomMateDao roomMateDao;
    @Autowired	AddressDao addDao;
    @Autowired	ShApartmentDao shApartmentDao;
	
	public RoomMateServiceImpl() {	
    }
	
	@Autowired
	public RoomMateServiceImpl(RoomMateDao roomMateDao){
		this.roomMateDao = roomMateDao;
	}

    public RoomMate getRoomMate(Long id) {
    	return roomMateDao.findOne(id);
    }
    
	public Iterable<RoomMate> getRoomMates() {
		return roomMateDao.findAll();
	}

	public RoomMateForm saveFrom(RoomMateForm roomMateForm) {
		RoomMate roomMate = new RoomMate();
		roomMate.setPerson(new Person());
		roomMate.setFirstName(roomMateForm.getFirstName());
		roomMate.setLastName(roomMateForm.getLastName());
		roomMate.setAge(roomMateForm.getAge());
        roomMate.setSex(roomMateForm.getSex());
        roomMate.setDescription(roomMateForm.getDescription());
		roomMate.setShApartment(shApartmentDao.findOne(roomMateForm.getAdId()));
		roomMateDao.save(roomMate);
		return roomMateForm;
	}

	@Transactional
	public RoomMateForm fillRoomMateForm(RoomMate roomMate) {
		RoomMateForm roomMateForm = new RoomMateForm();
		roomMateForm.setFirstName(roomMate.getFirstName());
		roomMateForm.setLastName(roomMate.getLastName());
		roomMateForm.setAge(roomMate.getAge());
		roomMateForm.setSex(roomMate.getSex());
		roomMateForm.setDescription(roomMate.getDescription());
		return roomMateForm;
	}

	@Override
	public RoomMate loadRoomMate() {
		
		return roomMateDao.findOne(2L);
	}

	@Override
	public Iterable<RoomMate> getRoomMates(Long adId) {
		ShApartment shAp = shApartmentDao.findOne(adId);
		return roomMateDao.findByShApartment(shAp);

	}
	
}

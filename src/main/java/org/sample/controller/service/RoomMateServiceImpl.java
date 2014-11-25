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

import java.util.Collection;


@Service
public class RoomMateServiceImpl implements RoomMateService {

    @Autowired    RoomMateDao roomMateDao;
    @Autowired    AddressDao addDao;
	
	public RoomMateServiceImpl() {	
    }
	
	@Autowired
	public RoomMateServiceImpl(RoomMateDao roomMateDao){
		this.roomMateDao = roomMateDao;
	}

    public RoomMate getRoomMate(Long id) {
    	return roomMateDao.findOne(id);
    }
    

//	public Iterable<RoomMate> getRoomMates() {
//		return roomMateDao.findAll();
//	}
//
//    public RoomMate loadRoomMateByEmail(String email) {
//    	return roomMateDao.findByEmail(email);
//    }
//
//    @Override
//    public RoomMateForm saveFrom(RoomMateForm roomMateForm) {
//        User user = loadUserByEmail(roomMateForm.getEmail());
//        user.setFirstName(roomMateForm.getFirstName());
//        user.setLastName(roomMateForm.getLastName());
//        user.setAge(roomMateForm.getAge());
//        user.setSex(roomMateForm.getSex());
//        user.setDescription(roomMateForm.getDescription());
//        userDao.save(user);
//        return roomMateForm;
//    }
//
	public RoomMateForm saveFrom(RoomMateForm RoomMateForm) {
		//RoomMate roomMate = loadUserByEmail(RoomMateForm.getEmail());
		RoomMate roomMate = new RoomMate();
		roomMate.setPerson(new Person());
		roomMate.setFirstName(RoomMateForm.getFirstName());
		roomMate.setLastName(RoomMateForm.getLastName());
		roomMate.setAge(RoomMateForm.getAge());
        roomMate.setSex(RoomMateForm.getSex());
		//roomMate.setIsNew(false);
		roomMateDao.save(roomMate);
		return RoomMateForm;
	}

	//@Override
	@Transactional
	public RoomMateForm fillRoomMateForm(RoomMate roomMate) {
		RoomMateForm roomMateForm = new RoomMateForm();
		roomMateForm.setFirstName(roomMate.getFirstName());
		roomMateForm.setLastName(roomMate.getLastName());
		//roomMateForm.setEmail(roomMate.getEmail());
		roomMateForm.setAge(roomMate.getAge());
		roomMateForm.setSex(roomMate.getSex());
		roomMateForm.setDescription(roomMate.getDescription());
		return roomMateForm;
	}

//	private RoomMate getRoomMate(int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public RoomMate loadRoomMate() {
		
		return roomMateDao.findOne(2L);
	}
	
}

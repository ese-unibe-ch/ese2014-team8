package org.sample.controller.service;

import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.RoomMateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class RoomMateServiceImpl implements RoomMateService {

/*    @Autowired    RoomMateDao userDao;
    @Autowired    AddressDao addDao;
	
	
	public RoomMateServiceImpl() {
    }

    public RoomMate getRoomMate(Long id) {
    	return roomMateDao.findOne(id);
    }

	public Iterable<RoomMate> getRoomMates() {
		return roomMateDao.findAll();
	}

    public RoomMate loadRoomMateByEmail(String email) {
    	return roomMateDao.findByEmail(email);
    }

    @Override
    public RoomMateForm saveFrom(RoomMateForm roomMateForm) {
        User user = loadUserByEmail(roomMateForm.getEmail());
        user.setFirstName(roomMateForm.getFirstName());
        user.setLastName(roomMateForm.getLastName());
        user.setAge(roomMateForm.getAge());
        user.setSex(roomMateForm.getSex());
        user.setDescription(roomMateForm.getDescription());
        userDao.save(user);
        return roomMateForm;
    }

	public NewRoomMateForm saveFrom(NewRoomMateForm newRoomMateForm) {
		User user = loadUserByEmail(newRoomMateForm.getEmail());
		user.setPerson(new Person());
		user.setFirstName(newRoomMateForm.getFirstName());
		user.setLastName(newRoomMateForm.getLastName());
		user.setAge(newRoomMateForm.getAge());
        user.setSex(newRoomMateForm.getSex());
		user.setIsNew(false);
		userDao.save(user);
		return newRoomMateForm;
	}

	@Override
	@Transactional
	public RoomMateForm fillRoomMateForm(RoomMate roomMate) {
		RoomMateForm roomMateForm = new RoomMateForm();
		roomMateForm.setFirstName(roomMate.getFirstName());
		roomMateForm.setLastName(roomMate.getLastName());
		roomMateForm.setEmail(roomMate.getEmail());
		roomMateForm.setAge(roomMate.getAge());
		roomMateForm.setSex(roomMate.getSex());
		roomMateForm.setDescription(roomMate.getDescription());
		return roomMateForm;
	}
	*/
}

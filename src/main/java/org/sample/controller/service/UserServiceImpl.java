package org.sample.controller.service;

import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.PersonDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    @Autowired	PersonDao personDao;
	
	
	public UserServiceImpl() {
    }

    public User getUser(Long id) {
    	return userDao.findOne(id);
    }

	public Iterable<User> getUsers() {
		return userDao.findAll();
	}

    public User loadUserByEmail(String email) {
    	return userDao.findByEmail(email);
    }

    @Override
    public ProfileForm saveFrom(ProfileForm profileForm) {
        User user = loadUserByEmail(profileForm.getEmail());
        user.setFirstName(profileForm.getFirstName());
        user.setLastName(profileForm.getLastName());
        user.setAge(profileForm.getAge());
        user.setSex(profileForm.getSex());
        user.setDescription(profileForm.getDescription());
		user.setPicture(profileForm.getPicture());
        userDao.save(user);
        return profileForm;
    }

	public NewProfileForm saveFrom(NewProfileForm newProfileForm) {
		User user = loadUserByEmail(newProfileForm.getEmail());
		user.setPerson(new Person());
		user.setFirstName(newProfileForm.getFirstName());
		user.setLastName(newProfileForm.getLastName());
		user.setAge(newProfileForm.getAge());
        user.setSex(newProfileForm.getSex());
		user.setIsNew(false);
		userDao.save(user);
		return newProfileForm;
	}

	@Override
	@Transactional
	public ProfileForm fillProfileForm(User user) {
		ProfileForm profileForm = new ProfileForm();
		profileForm.setFirstName(user.getFirstName());
		profileForm.setLastName(user.getLastName());
		profileForm.setEmail(user.getEmail());
		profileForm.setAge(user.getAge());
		profileForm.setSex(user.getSex());
		profileForm.setDescription(user.getDescription());
		return profileForm;
	}

	@Override
	public Person getPerson(Long pId) {
		return personDao.findOne(pId);
	}

	@Override
	public Map<String, String> getUpdates(User user) {
		Map<String, String > updates = new HashMap<String,String>();
		
		if(user.getLastMainHit()!=null){
			updates.put("messageUpdate", getMessageUpdate(user));
			updates.put("upcomingVisits", getUpcomingVisitsMessage(user));
		}
		user.setLastMainHit(new Date());
		userDao.save(user);
		return updates;
	}

	private String getUpcomingVisitsMessage(User user) {
		int visitCounter = 0;
		Date now = new Date();
		for(TimeSlot visit:user.getRegisteredTimeSlots()){
			if(visit.getDateTime().after(now)){
				visitCounter++;
			}
		}
		if(visitCounter!=0){
			StringBuffer upcomingVisits = new StringBuffer();
			upcomingVisits.append("You have ");
			upcomingVisits.append(visitCounter);
			upcomingVisits.append(" upcoming <a href=\"/upcomingVisits\" class=\"alert-link\">visits</a>.");
			return upcomingVisits.toString();
		}
		else{
			return null;
		}
	}

	private String getMessageUpdate(User user) {
		int messageCounter = 0;
		for(Message message:user.getReceivedMessages()){
			if(!message.isMessageRead()){
				messageCounter++;
			}
		}
		if(messageCounter!=0){
			StringBuffer messageUpdate = new StringBuffer();
			messageUpdate.append("You have ");
			messageUpdate.append(messageCounter);
			messageUpdate.append(" unread <a href=\"/messages\" class=\"alert-link\">messages</a>.");
			return messageUpdate.toString();
		}
		else{
			return null;
		}
		
	}

	@Override
	public User imageSaved(User user) {
		user.setImageSaved(true);
		userDao.save(user);
		return user;
		
	}

	
    

	
	
}

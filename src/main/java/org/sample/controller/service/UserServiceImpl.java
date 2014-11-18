package org.sample.controller.service;

import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.AddressDao;

import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
	
	
	public UserServiceImpl() {
    }

    public User getUser(Long id) {
    	return userDao.findOne(id);
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
        userDao.save(user);
        return profileForm;
    }

	public NewProfileForm saveFrom(NewProfileForm newProfileForm) {
		User user = loadUserByEmail(newProfileForm.getEmail());
		user.setFirstName(newProfileForm.getFirstName());
		user.setLastName(newProfileForm.getLastName());
		user.setAge(newProfileForm.getAge());
        user.setSex(newProfileForm.getSex());
		user.setIsNew(false);
		userDao.save(user);
		return newProfileForm;
	}

	
    

	
	
}

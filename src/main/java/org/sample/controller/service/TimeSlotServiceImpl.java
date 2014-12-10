package org.sample.controller.service;


import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.ShApartmentDao;
import org.sample.model.dao.TimeSlotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class TimeSlotServiceImpl implements TimeSlotService {

	@Autowired	  ApartmentDao apDao;
	@Autowired	  ShApartmentDao shApDao;
	@Autowired	TimeSlotDao timeSlotDao;
	
	public TimeSlotServiceImpl() {
    }
	

	@Override
	public Collection<TimeSlot> addTimeSlot (TimeSlotForm timeSlotForm) throws InvalidDateException{
		TimeSlot timeSlot = new TimeSlot();
		if(!isFutureDate(timeSlotForm.getDate())){
			throw new InvalidDateException("Date is not valid!");
		}
		Date dateTime = new Date(timeSlotForm.getDate().getTime()+timeSlotForm.getTime().getTime()+(60*60*1000));
		timeSlot.setDateTime(dateTime);
		timeSlot.setMaxNumVisitors(timeSlotForm.getMaxNumVisitors());
		timeSlot.setPlacesLeft(timeSlot.getMaxNumVisitors());
		
		
		if(timeSlotForm.getCategory().equals("Apartment")){
			timeSlot.setApartment(apDao.findOne(timeSlotForm.getAdId()));
			timeSlotDao.save(timeSlot);
		}
		else if(timeSlotForm.getCategory().equals("Shared Apartment")){
			timeSlot.setShApartment(shApDao.findOne(timeSlotForm.getAdId()));
			timeSlotDao.save(timeSlot);	
		}
		
		return getTimeSlots(timeSlotForm.getCategory(), timeSlotForm.getAdId());
	}
	
	private boolean isFutureDate(Date date) {
    	java.util.Date now = new java.util.Date();
    	return date.after(now);
	}

	@Override
	public void deleteTimeSlot(long id) {
		timeSlotDao.delete(id);
		
	}

	@Override
	public Collection<TimeSlot> getTimeSlots(String adCategory, long adId) {
		Collection<TimeSlot> timeSlots = Collections.emptySet();
		if(adCategory.equals("Apartment")){
			timeSlots = timeSlotDao.findByApartment(apDao.findOne(adId));
		}
		else if(adCategory.equals("Shared Apartment")){
			timeSlots = timeSlotDao.findByShApartment(shApDao.findOne(adId));
		}
		
		
		return timeSlots;
	}

	@Override
	@Transactional
	public void registerTimeSlot(long timeSlotId, User user) {
		TimeSlot timeSlot = timeSlotDao.findOne(timeSlotId);
		Collection<User> visitors = timeSlot.getVisitors();
		visitors.add(user);
		timeSlot.setVisitors(visitors);
		int oldPlacesLeft = timeSlot.getPlacesLeft();
		timeSlot.setPlacesLeft(oldPlacesLeft - 1);
		timeSlotDao.save(timeSlot);
		
	}

	
	
}

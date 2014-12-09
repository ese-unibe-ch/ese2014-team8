package org.sample.controller.service;

import java.util.Collection;
import java.util.List;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;

public interface TimeSlotService {

	public Collection<TimeSlot> addTimeSlot (TimeSlotForm timeslot)throws InvalidDateException;
	public void deleteTimeSlot(long id);
	public Collection<TimeSlot> getTimeSlots(String adCategory, long adId);
	public void registerTimeSlot(long parseLong, User loadUserByEmail);

}

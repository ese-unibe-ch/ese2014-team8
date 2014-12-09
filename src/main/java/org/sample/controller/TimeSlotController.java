package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.pojos.*;
import org.sample.controller.service.AdService;
import org.sample.controller.service.TimeSlotService;
import org.sample.controller.service.UserService;
import org.sample.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TimeSlotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeSlotController.class);

    @Autowired
    TimeSlotService timeSlotService;
    @Autowired
    UserService userService;
    @Autowired
    AdService adService;
     
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		timeFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, "time", new CustomDateEditor(timeFormat, true));
	}
    
    @RequestMapping(value="/timeslots/{adCategory}/{adId}", method = RequestMethod.GET)
    public Object timeslots(HttpServletRequest request, @PathVariable String adCategory, @PathVariable String adId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	TimeSlotForm timeSlotForm = new TimeSlotForm();
    	
    	timeSlotForm.setAdId(Long.parseLong(adId));
    	timeSlotForm.setCategory(adCategory);
    	ModelAndView model = new ModelAndView("timeslots");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("timeSlotForm", timeSlotForm);
    	model.addObject("timeSlots", timeSlotService.getTimeSlots(adCategory, Long.parseLong(adId)));
    	return model;
    }
    
    
    @RequestMapping(value="/timeslots", method = RequestMethod.POST)
    public Object submitTimeslots(HttpServletRequest request, TimeSlotForm timeSlot){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	ModelAndView model = new ModelAndView("timeslots");
    	try{
    		TimeSlotForm timeSlotForm = new TimeSlotForm();
        	timeSlotForm.setAdId(timeSlot.getAdId());
        	timeSlotForm.setCategory(timeSlot.getCategory());
        	
        	model.addObject("user", user);
        	model.addObject("timeSlots", timeSlotService.addTimeSlot(timeSlot));
        	model.addObject("timeSlotForm", timeSlotForm);
    	}catch(InvalidDateException e){
    		model.addObject("page_error", e.getMessage());
    		model.addObject("user", user);
    		model.addObject("timeSlots", timeSlotService.getTimeSlots(timeSlot.getCategory(), timeSlot.getAdId()));
    		model.addObject("timeSlotForm", timeSlot);
    	}
    	
    	return model;
    }
    
    @RequestMapping(value="/removeTimeslot/{adCategory}/{adId}/{timeSlotId}", method = RequestMethod.GET)
    public Object removeTimeslot(HttpServletRequest request, @PathVariable String adCategory, 
    		@PathVariable String adId, @PathVariable String timeSlotId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	timeSlotService.deleteTimeSlot(Long.parseLong(timeSlotId));
    	return "redirect:/timeslots/" + adCategory + "/" + adId;
    }
    
    @RequestMapping(value="/registerTimeslot/{adCategory}/{adId}/{timeSlotId}", method = RequestMethod.GET)
    public Object registerTimeslot(HttpServletRequest request, @PathVariable String adCategory, 
    		@PathVariable String adId, @PathVariable String timeSlotId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	timeSlotService.registerTimeSlot(Long.parseLong(timeSlotId), user);
    	
    	return "redirect:/searchresults/" + adCategory + "/" + adId;
        
    }
    
    @RequestMapping(value="/manageVisits/{adCategory}/{adId}", method = RequestMethod.GET)
    public Object manageVisits(HttpServletRequest request, @PathVariable String adCategory, @PathVariable String adId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	ModelAndView model = new ModelAndView("manageVisits");
    	model.addObject("user", user);
    	model.addObject("timeSlots", timeSlotService.getTimeSlots(adCategory, Long.parseLong(adId)));
    	model.addObject("category", adCategory);
    	model.addObject("ad",adService.getAd(adCategory, Long.parseLong(adId)));
    	return model;
    }
  
    @RequestMapping(value = "/upcomingVisits", method = RequestMethod.GET)
    public Object bookmarkedAds(HttpServletRequest request) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("upcomingVisits");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addObject("now", new Date());
    	return model;
    }

}



package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.controller.service.AdService;
import org.sample.controller.service.TimeSlotService;
import org.sample.controller.service.UserService;
import org.sample.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sample.model.TimeSlot;

@Controller
public class TimeSlotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeSlotController.class);

    @Autowired
    TimeSlotService timeSlotService;
    @Autowired
    UserService userService;
     
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm", Locale.getDefault());
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
    	
    	TimeSlotForm timeSlotForm = new TimeSlotForm();
    	timeSlotForm.setAdId(timeSlot.getAdId());
    	timeSlotForm.setCategory(timeSlot.getCategory());
    	
    	ModelAndView model = new ModelAndView("timeslots");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("timeSlots", timeSlotService.addTimeSlot(timeSlot));
    	model.addObject("timeSlotForm", timeSlotForm);
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
    
    
  

}



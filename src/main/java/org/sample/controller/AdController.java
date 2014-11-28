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
import org.sample.controller.service.UserService;
import org.sample.controller.service.RoomMateService;
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
public class AdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    @Autowired 
    RoomMateService roomMateService;
    
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
    	model.addObject("timeSlots", adService.getTimeSlots(adCategory, Long.parseLong(adId)));
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
    	model.addObject("timeSlots", adService.addTimeSlot(timeSlot));
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
    	adService.deleteTimeSlot(Long.parseLong(timeSlotId));
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
    	adService.registerTimeSlot(Long.parseLong(timeSlotId), user);
    	
    	return "redirect:/searchresults/" + adCategory + "/" + adId;
        
    }
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public Object main(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("main");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	
        SecurityContext ctx = SecurityContextHolder.getContext();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("signupForm", new SignupForm());
        if (ctx.getAuthentication() != null) {
            mav.addObject("user", userService.loadUserByEmail(ctx.getAuthentication().getName()));
            mav.addObject("username", ctx.getAuthentication().getName());
        } else {
            mav.addObject("user", userService.loadUserByEmail(ctx.getAuthentication().getName()));
            mav.addObject("username", "none");
        }
        return mav;
    }
    

    @RequestMapping(value="/myAds", method = RequestMethod.GET) 
    public Object myAds(HttpServletRequest request){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }    	
    	ModelAndView model = new ModelAndView("myAds");
    	String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
    	model.addObject("apartments",adService.getApartmentsByUser(userMail));
        model.addObject("shApartments",adService.getShApartmentsByUser(userMail));
        model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    @RequestMapping(value="/newAd", method = RequestMethod.GET) 
    public Object makeAd(HttpServletRequest request){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("newAd");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("apForm", new ApartmentForm());
    	return model;
    }

    @RequestMapping(value="/newSharedAd", method = RequestMethod.GET) 
    public Object makeSharedAd(HttpServletRequest request){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("newSharedAd");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("shApForm", new ShApartmentForm());
    	model.addObject("roomMates", roomMateService.getRoomMates());
    	return model;
    }
    
 
    @RequestMapping(value="/viewAd", method = RequestMethod.POST)
    public Object makeAd(HttpServletRequest request, ApartmentForm form, ShApartmentForm form2, BindingResult result){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = null;
    	if(form.getCategory().equals("Apartment")){   	
        	if (!result.hasErrors()) {
                try {
                    form.setUser(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                	Apartment apartment=adService.saveFrom(form);
                	model = new ModelAndView("viewAd");
                    model.addObject("message","This is what your ad will look like:");
                    model.addObject("category","Apartment");
                    model.addObject("ad", apartment);
                } catch (InvalidDateException e) {
                	model = new ModelAndView("newAd");
                	model.addObject("page_error", e.getMessage());
                }
            } else {
            	model = new ModelAndView("newAd");
            }   	
    	}
    	if(form2.getCategory().equals("Shared Apartment")){
        	if (!result.hasErrors()) {
                try {
                    form2.setUser(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                	ShApartment apartment=adService.saveFrom(form2);
                	model = new ModelAndView("viewAd");
                    model.addObject("message","This is what your ad will look like:");
                    model.addObject("category","Shared Apartment");
                    model.addObject("ad", apartment);
                } catch (InvalidDateException e) {
                	model = new ModelAndView("main");
                	model.addObject("page_error", e.getMessage());
                }
            } else {
            	model = new ModelAndView("main");
            }
    		
    	}
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }
    
    @RequestMapping(value="/editAd", method = RequestMethod.POST)
    public Object editAd(HttpServletRequest request, ApartmentForm apartmentForm, ShApartmentForm shApartmentForm, BindingResult result){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model;
    	
    	if (!result.hasErrors()) {
            model = new ModelAndView("editAd");
            if(apartmentForm.getCategory().equals("Apartment")){
            	Apartment oldAd = adService.getApAd(apartmentForm.getId());
            	apartmentForm = adService.fillInFormFrom(oldAd);
                model.addObject("category","Apartment");
               // model.addObject("oldAd", oldAd);
                model.addObject("apForm", apartmentForm);
            }
            else{
            	ShApartment oldAd = adService.getShApAd(shApartmentForm.getId());
            	shApartmentForm = adService.fillInFormFrom(oldAd);
            	model.addObject("category","Shared Apartment");
            	//model.addObject("oldAd", oldAd);
                model.addObject("shApForm", shApartmentForm);
            }
        } 
    	else {
        	model = new ModelAndView("index");
        }   	
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }

    @RequestMapping(value="/editAd/{adType}/{adId}")
    public Object editAdId(HttpServletRequest request, @PathVariable String adType, @PathVariable Long adId) {
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!request.isUserInRole("ROLE_PERSONA_USER") || !user.getIsAdmin()) {
            return "redirect:/";
        } else if(user.getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("editAd");
        model.addObject("category",adType);
        model.addObject("user",user);
        if(adType.equals("Apartment") && (user.getIsAdmin() || user.getId() == adService.getApAd(adId).getOwner().getId())) {
            model.addObject("apForm", adService.fillInFormFrom(adService.getApAd(adId)));
        } else if(adType.equals("Shared Apartment") && (user.getIsAdmin() || user.getId() == adService.getShApAd(adId).getOwner().getId())) {
            model.addObject("shApForm", adService.fillInFormFrom(adService.getShApAd(adId)));
        }
        return model;
    }
  

}



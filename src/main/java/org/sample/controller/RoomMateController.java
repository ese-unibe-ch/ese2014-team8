package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.controller.service.AdService;
import org.sample.controller.service.RoomMateService;
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

@Controller
public class RoomMateController {

//private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

//    @Autowired
//    AdService adService;
    @Autowired
    RoomMateService roomMateService;
    @Autowired
    UserService userService;
    
    
//    @RequestMapping(value = "/roomMate", method = RequestMethod.GET)
//    public String profile(HttpServletRequest request) {
//        ModelAndView model;
//        if(request.isUserInRole("ROLE_PERSONA_USER") && userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
//            return "redirect:/newRoomMate";
//        }
//        else if(request.isUserInRole("ROLE_PERSONA_USER")) {
//            return "redirect:/editRoomMate";
//        } else {
//            return "redirect:/";
//        }
//    }

    // view, add and delete RoomMates
    @RequestMapping(value = "/RoomMates", method = RequestMethod.POST)
    public Object editProfile(HttpServletRequest request, RoomMateForm roomMateForm) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	} else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	System.out.println(roomMateForm.getAdId());
        ModelAndView model = new ModelAndView("newRoomMate");
        SecurityContext ctx = SecurityContextHolder.getContext();
        RoomMate roomMate = roomMateService.loadRoomMate();
        model.addObject("roomMateForm", roomMateService.fillRoomMateForm(roomMate));
        roomMateService.saveFrom(roomMateForm);
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        //return model;
        return "redirect:/editAd/"+"Shared Apartment/"+Long.toString(roomMateForm.getAdId());
    }

    // view and add RoomMates
    @RequestMapping(value = "/RoomMates/{adId}", method = RequestMethod.GET)
    public Object newProfile(HttpServletRequest request, @PathVariable String adId) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("newRoomMate");
        SecurityContext ctx = SecurityContextHolder.getContext();
        RoomMateForm roomMateForm = new RoomMateForm();
        roomMateForm.setAdId(Long.parseLong(adId));
        model.addObject("roomMateForm", roomMateForm);
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }

//    @RequestMapping(value = "/saveRoomMate", method = RequestMethod.POST)
//    public String saveRoomMate(HttpServletRequest request, @Valid RoomMateForm roomMateForm, BindingResult result, RedirectAttributes redirectAttributes) {
//        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
//            return "redirect:/";
//        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
//            return "redirect:/profile";
//        }
//        //SecurityContext ctx = SecurityContextHolder.getContext();
//        //profileForm.setEmail(ctx.getAuthentication().getName());
//        roomMateService.saveFrom(roomMateForm);
//        //redirectAttributes.addFlashAttribute("RoomMate saved.");
//        return "redirect:/RoomMates/";
//    }
    
    @RequestMapping(value = "/roomMateList", method = RequestMethod.GET)
    public Object newProfile(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        
        ModelAndView model = new ModelAndView("roomMates");
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("roomMates", roomMateService.getRoomMates());
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }

//    @RequestMapping(value="/timeslots/{adCategory}/{adId}", method = RequestMethod.GET)
//    public Object timeslots(HttpServletRequest request, @PathVariable String adCategory, @PathVariable String adId){
//    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
//            return "redirect:/";
//        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
//            return "redirect:/profile";
//        }
//    	TimeSlotForm timeSlotForm = new TimeSlotForm();
//    	
//    	timeSlotForm.setAdId(Long.parseLong(adId));
//    	timeSlotForm.setCategory(adCategory);
//    	ModelAndView model = new ModelAndView("timeslots");
//    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
//    	model.addObject("timeSlotForm", timeSlotForm);
//    	model.addObject("timeSlots", adService.getTimeSlots(adCategory, Long.parseLong(adId)));
//    	return model;
//    }
//    
//    
//    @RequestMapping(value="/timeslots", method = RequestMethod.POST)
//    public Object submitTimeslots(HttpServletRequest request, TimeSlotForm timeSlot){
//    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
//            return "redirect:/";
//        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
//            return "redirect:/profile";
//        }
//    	
//    	TimeSlotForm timeSlotForm = new TimeSlotForm();
//    	timeSlotForm.setAdId(timeSlot.getAdId());
//    	timeSlotForm.setCategory(timeSlot.getCategory());
//    	
//    	ModelAndView model = new ModelAndView("timeslots");
//    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
//    	model.addObject("timeSlots", adService.addTimeSlot(timeSlot));
//    	model.addObject("timeSlotForm", timeSlotForm);
//    	return model;
//    }
//    
//    @RequestMapping(value="/removeTimeslot/{adCategory}/{adId}/{timeSlotId}", method = RequestMethod.GET)
//    public Object removeTimeslot(HttpServletRequest request, @PathVariable String adCategory, 
//    		@PathVariable String adId, @PathVariable String timeSlotId){
//    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
//            return "redirect:/";
//        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
//            return "redirect:/profile";
//        }
//    	adService.deleteTimeSlot(Long.parseLong(timeSlotId));
//    	return "redirect:/timeslots/" + adCategory + "/" + adId;
//    }    
    
//    @RequestMapping(value = "/saveNewProfile", method = RequestMethod.POST)
//    public String saveNewProfile(HttpServletRequest request, @Valid NewProfileForm newProfileForm, BindingResult result, RedirectAttributes redirectAttributes) {
//        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
//            return "redirect:/";
//        }
//        SecurityContext ctx = SecurityContextHolder.getContext();
//        newProfileForm.setEmail(ctx.getAuthentication().getName());
//        userService.saveFrom(newProfileForm);
//        redirectAttributes.addFlashAttribute("Profile created.");
//        return "redirect:/profile";
//    }
    
//    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
//    public String securityError(RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("page_error", "You do not have permission to do that!");
//        return "redirect:/";
//    }

}



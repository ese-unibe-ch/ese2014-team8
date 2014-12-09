package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.sample.controller.pojos.*;
import org.sample.controller.service.RoomMateService;
import org.sample.controller.service.UserService;
import org.sample.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RoomMateController {


    @Autowired
    RoomMateService roomMateService;
    @Autowired
    UserService userService;
    
    // view RoomMate in Ad
    @RequestMapping(value = "/RoomMates", method = RequestMethod.POST)
    public Object viewRoomMate(HttpServletRequest request, RoomMateForm roomMateForm) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	} else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("newRoomMate");
        SecurityContext ctx = SecurityContextHolder.getContext();
        roomMateService.saveFrom(roomMateForm);
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return "redirect:/editAd/"+"Shared Apartment/"+Long.toString(roomMateForm.getAdId());
    }

    // new or change RoomMate
    @RequestMapping(value = "/RoomMates/{adId}", method = RequestMethod.GET)
    public Object newRoomMate(HttpServletRequest request, @PathVariable Long adId) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("newRoomMate");
        SecurityContext ctx = SecurityContextHolder.getContext();
        RoomMateForm roomMateForm = new RoomMateForm();
        roomMateForm.setAdId(adId);
        model.addObject("roomMateForm", roomMateForm);
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }
    
    @RequestMapping(value = "/addUserAsRoomMate/{adId}", method = RequestMethod.GET)
    public Object addUserAsRoomMate(HttpServletRequest request, @PathVariable Long adId) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	} else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("newRoomMate");
        RoomMateForm roomMateForm = new RoomMateForm();
        
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByEmail(userMail);
        ProfileForm profileForm = userService.fillProfileForm(user);
        
        roomMateForm.setAdId(adId);
        roomMateForm.setFirstName(profileForm.getFirstName());
        roomMateForm.setLastName(profileForm.getLastName());
        roomMateForm.setAge(profileForm.getAge());
        roomMateForm.setSex(profileForm.getSex());
        roomMateForm.setDescription(profileForm.getDescription()); 
        model.addObject("roomMateForm", roomMateForm);
        model.addObject("user", user);
        return model;
    }
    
    // edit RoomMate
    @RequestMapping(value = "/editRoomMate/{roomMateId}", method = RequestMethod.GET)
    public Object editRoomMate(HttpServletRequest request, RoomMateForm roomMateForm,  @PathVariable Long roomMateId) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	} else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("newRoomMate");
        SecurityContext ctx = SecurityContextHolder.getContext();
        RoomMate roomMate = roomMateService.getRoomMate(roomMateId);
        model.addObject("roomMateForm", roomMateService.fillRoomMateForm(roomMate));
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }
    
    // delete RoomMate
    @RequestMapping(value="/deleteRoomMate/{adId}/{roomMateId}", method = RequestMethod.GET)
    public Object removeTimeslot(HttpServletRequest request,@PathVariable Long adId, @PathVariable Long roomMateId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	roomMateService.deleteRoomMate(roomMateId);
    	return "redirect:/editAd/"+"Shared Apartment/"+Long.toString(adId);
    }
    
}



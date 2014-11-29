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
    
    @RequestMapping(value = "/roomMateList", method = RequestMethod.GET)
    public Object roomMateList(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        
        ModelAndView model = new ModelAndView("roomMateTable");
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("roomMates", roomMateService.getRoomMates(3L));
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }
}



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
        //RoomMate roomMate = roomMateService.loadRoomMate();
        //model.addObject("roomMateForm", roomMateService.fillRoomMateForm(roomMate));
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
    
    @RequestMapping(value = "/roomMateList", method = RequestMethod.GET)
    public Object newProfile(HttpServletRequest request) {
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



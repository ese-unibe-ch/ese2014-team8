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
import org.sample.controller.service.MessageService;
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
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;
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
    
    @RequestMapping(value="/sendMessage", method = RequestMethod.POST)
    Object sendMessage(HttpServletRequest request, MessageForm enquiry){
    	System.out.println("before save");
    	messageService.saveFrom(enquiry);
    	System.out.println("after save");
    	return "redirect:/searchresults/"+ enquiry.getCategory() +"/"+ Long.toString(enquiry.getAdId());
    	
    }
    
    
    @RequestMapping(value="/messages", method = RequestMethod.GET)
    Object messages(HttpServletRequest request){
    	 if(!request.isUserInRole("ROLE_PERSONA_USER")) {
             return "redirect:/";
         } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
             return "redirect:/profile";
         }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()); 
    	ModelAndView model = new ModelAndView("messages");
    	model.addObject("user", user);
    	model.addObject("receivedMessages", messageService.getReceivedMessages(user));
    	model.addObject("sentMessages", messageService.getSentMessages(user));
    	return model;
    }
    
    @RequestMapping(value="/readMessage/{messageId}", method = RequestMethod.GET)
    Object readMessage(HttpServletRequest request, @PathVariable String messageId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()); 
    	messageService.markMessageRead(Long.parseLong(messageId));
    	ModelAndView model = new ModelAndView("readMessage");
    	model.addObject("user", user);
    	model.addObject("message", messageService.getMessage(Long.parseLong(messageId)));
    	model.addObject("messageForm", new MessageForm());
    	
    	return model;
    }
    
    @RequestMapping(value="/reply", method = RequestMethod.POST)
    Object reply(HttpServletRequest request, MessageForm messageForm){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	messageService.saveFrom(messageForm);
    	return "redirect:/messages";
    }
    
}



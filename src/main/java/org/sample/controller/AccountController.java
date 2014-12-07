package org.sample.controller;

import javax.servlet.http.HttpServletRequest;
import org.sample.controller.pojos.*;
import org.sample.controller.service.AdService;
import org.sample.controller.service.UserService;
import org.sample.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public Object main(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	
        ModelAndView model = new ModelAndView("search");
    	SearchForm searchForm = new SearchForm();
    	searchForm.setCategories(adService.getCategories());
    	model.addObject("searchForm", searchForm);
    	model.addObject("user",user);
    	
    	model.addAllObjects(userService.getUpdates(user));
    	
        return model;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object index() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        ModelAndView mav = new ModelAndView("index");
        //mav.addObject("signupForm", new SignupForm());
        if (ctx.getAuthentication() != null) {
            //mav.addObject("user", userService.loadUserByEmail(ctx.getAuthentication().getName()));
            //mav.addObject("username", ctx.getAuthentication().getName());
        } else {
            //mav.addObject("user", userService.loadUserByEmail(ctx.getAuthentication().getName()));
            //mav.addObject("username", "none");
        }
        return mav;
    }
    
    @RequestMapping(value = "/placedAds", method = RequestMethod.GET)
    public Object placedAds(HttpServletRequest request) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	ModelAndView model = new ModelAndView("myAds");
    	model.addObject("user",user);
    	 model.addObject("apartments",adService.getApartmentsByUser(user.getEmail()));
         model.addObject("shApartments",adService.getShApartmentsByUser(user.getEmail()));
        return model;
    }
    
    
    @RequestMapping(value = "/searchAlerts", method = RequestMethod.GET)
    public Object searchAlerts(HttpServletRequest request) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("main");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    
    @RequestMapping(value = "/interestedPeople", method = RequestMethod.GET)
    public Object interestedPeople(HttpServletRequest request) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("main");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    
   
}

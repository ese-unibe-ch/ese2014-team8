package org.sample.controller;

import org.sample.controller.service.AdService;
import org.sample.controller.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zilti on 24.11.2014.
 */
@Controller
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public Object adminPage(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("admin");
        model.addObject("apartmentAds", adService.getApartments());
        model.addObject("shApartmentAds", adService.getShApartments());
        model.addObject("users", userService.getUsers());
        model.addObject("user", userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }

}

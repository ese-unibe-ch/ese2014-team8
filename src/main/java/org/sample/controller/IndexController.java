package org.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.controller.service.SampleService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    SampleService sampleService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("index");

    	model.addObject("signupForm", new SignupForm());
        model.addObject("teams", sampleService.getAllTeams());
        return model;
    }
    
    @RequestMapping(value = "/new-ad", method = RequestMethod.GET)
    public ModelAndView newAd(){
    	ModelAndView model = new ModelAndView("newAd");
    	
    	model.addObject("adForm", new AdForm());
    	
    	return model;
    }
    
    @RequestMapping(value="/makeAd", method = RequestMethod.POST)
    public ModelAndView makeAd(AdForm adForm, BindingResult result){
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	sampleService.saveFrom(adForm);
            	model = new ModelAndView("show");
                model.addObject("message","Ad added!");
            } catch (InvalidUserException e) {
            	model = new ModelAndView("newAd");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("index");
        }   	
    	return model;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	sampleService.saveFrom(signupForm);
            	model = new ModelAndView("show");
                model.addObject("message","Sign Up Complete!");
            } catch (InvalidUserException e) {
            	model = new ModelAndView("index");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("index");
        }   	
    	return model;
    }

    @RequestMapping(value = "/new-team", method = RequestMethod.GET)
    public ModelAndView newTeam() {
        ModelAndView model = new ModelAndView("newTeam");
        model.addObject("teamCreationForm", new TeamCreationForm());
        return model;
    }

    @RequestMapping(value = "/create-team", method = RequestMethod.POST)
    public ModelAndView createTeam(@Valid TeamCreationForm teamCreationForm, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView model;
        if(!result.hasErrors()) {
            try {
                sampleService.saveTeamFrom(teamCreationForm);
                model = new ModelAndView("show");
                model.addObject("message","Team creation complete!");
            } catch (Exception e) {
                model = new ModelAndView("newTeam");
                model.addObject("page_error", e.getMessage());
            }
        }
        else {
            model = new ModelAndView("newTeam");
        }
        return model;
    }

    @RequestMapping(value = "/login.jsp")
    public ModelAndView login() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        ModelAndView mav = new ModelAndView();
        if (ctx.getAuthentication() != null) {
            mav.addObject("username", ctx.getAuthentication().getName());
        } else {
            mav.addObject("username", "none");
        }
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/profile.jsp", method = RequestMethod.GET)
    public ModelAndView profile(String userId) {
        ModelAndView model = new ModelAndView("profile");
        Long lUserId = Long.parseLong(userId);
        User user = sampleService.getUser(lUserId);
        model.addObject("user",user);
        return model;
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do not have permission to do that!");
        return "redirect:/";
    }

}



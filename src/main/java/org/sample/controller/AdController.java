package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.controller.service.AdService;
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
public class AdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
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
    	model.addObject("shApForm", new ShApartmentForm());
    	return model;
    }
    
 
    @RequestMapping(value="/viewAd", method = RequestMethod.POST)
    public Object makeAd(HttpServletRequest request, ApartmentForm form, ShApartmentForm form2, BindingResult result){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	//System.out.println(form.getCategory());
    	ModelAndView model = null;
    	if(form.getCategory().equals("Apartment")){   	
        	if (!result.hasErrors()) {
                try {
                    form.setUser(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                	Apartment apartment=adService.saveFrom(form);
                	model = new ModelAndView("viewAd");
                    model.addObject("message","This is what your ad will look like:");
                    model.addObject("category","Apartment");
                    //apartment.setDescription(apartment.getDescription().replace("\n", "<br />\n"));
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
                    //form2.setDescription(form2.getDescription().replace("\n", "<br />\n"));
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
            	Apartment oldAd = adService.getAd(apartmentForm.getId());
            	apartmentForm = adService.saveFrom(oldAd);
                model.addObject("editType","Apartment");
                model.addObject("oldAd", oldAd);
                model.addObject("apForm", apartmentForm);
                model.addObject("shApForm", new ShApartmentForm());
            }
            else{
            	ShApartment oldAd = adService.getShApAd(shApartmentForm.getId());
            	shApartmentForm = adService.saveFrom(oldAd);
            	model.addObject("editType","Shared Apartment");
            	model.addObject("oldAd", oldAd);
                model.addObject("shApForm", shApartmentForm);
                model.addObject("apForm", new ApartmentForm());
            }
        } 
    	else {
        	model = new ModelAndView("index");
        }   	
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }


  

}


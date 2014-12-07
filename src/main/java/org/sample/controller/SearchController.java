package org.sample.controller;

import javax.servlet.http.HttpServletRequest;
import org.sample.controller.pojos.*;
import org.sample.controller.service.AdService;
import org.sample.controller.service.BookMarkService;
import org.sample.controller.service.UserService;
import org.sample.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    @Autowired
    BookMarkService bookMarkService;
    
    
    /**
     * Brings the user to a search page with a search form backed with a searchForm POJO object. 
     * The user must be logged in otherwise he will be redirected to "/"
     * @param request the HttpServletRequest to get the user role to check whether the user is logged in.
     * @return The search modelAndView with the searchForm and the user attached as an object.
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Object search(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("search");
    	SearchForm searchForm = new SearchForm();
    	searchForm.setCategories(adService.getCategories());
    	model.addObject("searchForm", searchForm);
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    /**
     * Gets the binded search criteria, gets the matching results from autowired sampleservice and returns a searchResults modelAndView with the results added as an object.
     * The user should be logged in otherwise he will be redirected to "/"
     * @param request the httpServletRequest containing the user role, to check whether user is logged in.
     * @param searchForm a pojo containing the search criteria
     * @param result the bindingresult
     * @return a modelAndview searchResults with the searchresults from the database added as an object.
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Object search(HttpServletRequest request, SearchForm searchForm, BindingResult result){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            model = new ModelAndView("searchResults");
            Iterable<? extends RealEstate> searchresults = adService.getSearchResults(searchForm);
            model.addObject("searchResults",searchresults);
            model.addObject("category", searchForm.getCategory());
        } else {
        	model = new ModelAndView("index");
        }   	
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }
    /**
     * Gets the user (who should be logged in) from the searchResults to a specific search result with the id adId.
     * @param request the httpServletRequest containing the user role, to check whether user is logged in. 
     * @param adId the id of the ad that has to be displayed
     * @return a modelAndView displaying the ad information with the ad and the user attached as objects.
     */
    @RequestMapping(value="/searchresults/{category}/{adId}",	method=RequestMethod.GET)
    public	Object displayAd(HttpServletRequest request, @PathVariable Long adId, @PathVariable	String	category)	{
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("showAd");
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	RealEstate ad = adService.getAd(category, adId);
    	model.addObject("ad", ad);
    	model.addObject("category", category);
    	model.addObject("isBookMarked", bookMarkService.isBookMarked(user, category, adId));
    	model.addObject("user",user);
    	model.addObject("messageForm", new MessageForm());
    	return model;
    }

}



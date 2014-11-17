package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.controller.service.SampleService;
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
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    SampleService sampleService;
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
    	searchForm.setCategories(sampleService.getCategories());
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
            try {
            	model = new ModelAndView("searchResults");
            	Iterable<? extends RealEstate> searchresults = sampleService.getSearchResults(searchForm);
            	model.addObject("searchResults",searchresults);
            } catch (InvalidUserException e) {
            	model = new ModelAndView("search");
            	model.addObject("page_error", e.getMessage());
            }
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
    @RequestMapping(value="/searchresults/{adId}",	method=RequestMethod.GET)
    public	Object displayAd(HttpServletRequest request, @PathVariable	String	adId)	{
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("showAd");
    	Long lAdId = Long.parseLong(adId);
    	RealEstate ad = sampleService.getAd(lAdId);
    	model.addObject("ad", ad);
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
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
                	Apartment apartment=sampleService.saveFrom(form);
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
                	ShApartment apartment=sampleService.saveFrom(form2);
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
            	Apartment oldAd = sampleService.getAd(apartmentForm.getId());
            	apartmentForm = sampleService.saveFrom(oldAd);
                model.addObject("editType","Apartment");
                model.addObject("oldAd", oldAd);
                model.addObject("apForm", apartmentForm);
                model.addObject("shApForm", new ShApartmentForm());
            }
            else{
            	ShApartment oldAd = sampleService.getShApAd(shApartmentForm.getId());
            	shApartmentForm = sampleService.saveFrom(oldAd);
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


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request) {
        ModelAndView model;
        if(request.isUserInRole("ROLE_PERSONA_USER") && userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/newProfile";
        }
        else if(request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/editProfile";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public Object editProfile(HttpServletRequest request) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	} else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("profile");
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addObject("profileForm", new ProfileForm());
        model.addObject("user",userService.loadUserByEmail(userMail));
        model.addObject("apartments",sampleService.getApartmentsByUser(userMail));
        model.addObject("shApartments",sampleService.getShApartmentsByUser(userMail));
        return model;
    }

    @RequestMapping(value = "/newProfile", method = RequestMethod.GET)
    public Object newProfile(HttpServletRequest request) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        }
        ModelAndView model = new ModelAndView();
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("profileForm", new ProfileForm());
        model.addObject("user",userService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }

    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public String saveProfile(HttpServletRequest request, @Valid ProfileForm profileForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        SecurityContext ctx = SecurityContextHolder.getContext();
        profileForm.setEmail(ctx.getAuthentication().getName());
        userService.saveFrom(profileForm);
        redirectAttributes.addFlashAttribute("Profile saved.");
        return "redirect:/profile";
    }

    @RequestMapping(value = "/saveNewProfile", method = RequestMethod.POST)
    public String saveNewProfile(HttpServletRequest request, @Valid NewProfileForm newProfileForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        }
        SecurityContext ctx = SecurityContextHolder.getContext();
        newProfileForm.setEmail(ctx.getAuthentication().getName());
        userService.saveFrom(newProfileForm);
        redirectAttributes.addFlashAttribute("Profile created.");
        return "redirect:/profile";
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do not have permission to do that!");
        return "redirect:/";
    }

}



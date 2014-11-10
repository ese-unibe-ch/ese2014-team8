package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;

import org.sample.controller.pojos.*;
import org.sample.controller.service.SampleService;
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
    
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
    	ModelAndView model = new ModelAndView("main");
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	/*ModelAndView model = new ModelAndView("index");

    	model.addObject("signupForm", new SignupForm());
        model.addObject("teams", sampleService.getAllTeams());
        return model;*/

        SecurityContext ctx = SecurityContextHolder.getContext();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("signupForm", new SignupForm());
        mav.addObject("teams", sampleService.getAllTeams());
        if (ctx.getAuthentication() != null) {
            mav.addObject("user", sampleService.loadUserByEmail(ctx.getAuthentication().getName()));
            mav.addObject("username", ctx.getAuthentication().getName());
        } else {
            mav.addObject("user", sampleService.loadUserByEmail(ctx.getAuthentication().getName()));
            mav.addObject("username", "none");
        }
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search() {
    	ModelAndView model = new ModelAndView("search");
    	SearchForm searchForm = new SearchForm();
    	searchForm.setCategories(sampleService.getCategories());
    	model.addObject("searchForm", searchForm);
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(SearchForm searchForm, BindingResult result){
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	model = new ModelAndView("searchResults");
            	Iterable<Apartment> searchresults = sampleService.getSearchResults(searchForm);
            	model.addObject("searchResults",searchresults);
            } catch (InvalidUserException e) {
            	model = new ModelAndView("search");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("index");
        }   	
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }
    
    @RequestMapping(value="/searchresults/{adId}",	method=RequestMethod.GET)
    public	ModelAndView displayAd(@PathVariable	String	adId)	{
    	ModelAndView model = new ModelAndView("showAd");
    	Long lAdId = Long.parseLong(adId);
    	RealEstate ad = sampleService.getAd(lAdId);
    	model.addObject("ad", ad);
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }

    @RequestMapping(value="/newAd", method = RequestMethod.GET) 
    public ModelAndView makeAd(){
    	ModelAndView model = new ModelAndView("newAd");
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("apForm", new ApartmentForm());
    	model.addObject("shApForm", new ShApartmentForm());
    	return model;
    }
    
 
    @RequestMapping(value="/viewAd", method = RequestMethod.POST)
    public ModelAndView makeAd(ApartmentForm form, ShApartmentForm form2, BindingResult result){
    	//System.out.println(form.getCategory());
    	ModelAndView model = null;
    	if(form.getCategory().equals("Apartment")){
    		//System.out.println("apartment cat");    	
        	if (!result.hasErrors()) {
                try {
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
    		System.out.println("shared apartment cat");
        	if (!result.hasErrors()) {
                try {
                	ShApartment apartment=sampleService.saveFrom(form2);
                	System.out.println("yes");
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
            	System.out.println("else");
            	model = new ModelAndView("main");
            }
    		
    	}
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	return model;
    }
    
    @RequestMapping(value="/editAd", method = RequestMethod.POST)
    public ModelAndView editAd(ApartmentForm apartmentForm, ShApartmentForm shApartmentForm, BindingResult result){
    	ModelAndView model;
    	
    	if (!result.hasErrors()) {
            model = new ModelAndView("editAd");
            if(apartmentForm.getCategory().equals("Apartment")){
            	System.out.println("edit Post, Apartment");
            	Apartment oldAd = sampleService.getAd(apartmentForm.getId());
            	apartmentForm = sampleService.saveFrom(oldAd);
                model.addObject("editType","Apartment");
                model.addObject("oldAd", oldAd);
                model.addObject("apForm", apartmentForm);//mg
                model.addObject("shApForm", new ShApartmentForm());
            }
            else{
            	ShApartment oldAd = sampleService.getShApAd(shApartmentForm.getId());
            	System.out.println("edit Post, SharedApartment");
            	shApartmentForm.setDescription(oldAd.getDescription());
            	shApartmentForm.setFixedMoveIn(oldAd.isFixedMoveIn());
            	shApartmentForm.setFixedMoveOut(oldAd.isFixedMoveOut());
            	shApartmentForm.setRoomSize(oldAd.getRoomSize());
            	model.addObject("editType","Shared Apartment");
            	model.addObject("oldAd", oldAd);
                model.addObject("shApForm", shApartmentForm);
                model.addObject("apForm", new ApartmentForm());
            }
        } 
    	else {
        	model = new ModelAndView("index");
        }   	
    	model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
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


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request) {
        ModelAndView model;
        if(request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/editProfile";
        } else if(request.isUserInRole("ROLE_NEW_USER")) {
            return "redirect:/newProfile";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public Object editProfile(HttpServletRequest request) {
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	}
        ModelAndView model = new ModelAndView("profile");
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("profileForm", new ProfileForm());
        model.addObject("user",sampleService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return model;
    }

    @PreAuthorize("hasRole('ROLE_PERSONA_USER')")
    @RequestMapping(value = "/newProfile", method = RequestMethod.GET)
    public ModelAndView newProfile() {
        ModelAndView model = new ModelAndView();
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("profileForm", new ProfileForm());
        model.addObject("user",sampleService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }

    @PreAuthorize("hasRole('ROLE_PERSONA_USER')")
    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult result, RedirectAttributes redirectAttributes) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        profileForm.setEmail(ctx.getAuthentication().getName());
        sampleService.saveFrom(profileForm);
        redirectAttributes.addFlashAttribute("Profile saved.");
        return "redirect:/profile";
    }

    @PreAuthorize("hasRole('ROLE_PERSONA_USER')")
    @RequestMapping(value = "/saveNewProfile", method = RequestMethod.POST)
    public String saveNewProfile(@Valid NewProfileForm newProfileForm, BindingResult result, RedirectAttributes redirectAttributes) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        newProfileForm.setEmail(ctx.getAuthentication().getName());
        sampleService.saveFrom(newProfileForm);
        redirectAttributes.addFlashAttribute("Profile created.");
        return "redirect:/profile";
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do not have permission to do that!");
        return "redirect:/";
    }

}



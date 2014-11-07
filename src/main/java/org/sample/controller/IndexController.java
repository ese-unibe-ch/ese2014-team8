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
    	return model;
    }
    
    @RequestMapping(value="/searchresults/{adId}",	method=RequestMethod.GET)
    public	ModelAndView displayAd(@PathVariable	String	adId)	{
    	ModelAndView model = new ModelAndView("showAd");
    	Long lAdId = Long.parseLong(adId);
    	RealEstate ad = sampleService.getAd(lAdId);
    	model.addObject("ad", ad);
    	return model;
    }

    @RequestMapping(value="/newAd", method = RequestMethod.GET) //mg
    public ModelAndView makeAd(){
    	ModelAndView model = new ModelAndView("newAd");
    	model.addObject("apForm", new ApartmentForm());
    	model.addObject("shApForm", new ShApartmentForm());
    	return model;
    }
    
    /*    @RequestMapping(value = "/new-ad", method = RequestMethod.GET)
>>>>>>> origin/AdAndSearch
    public ModelAndView newAd(){
    	ModelAndView model = new ModelAndView("newAd");
    	
    	model.addObject("apartmentForm", new ApartmentForm());
    	
    	return model;
<<<<<<< HEAD
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_NEW_USER')")
=======
    }*/
    
/*    @RequestMapping(value="/make-ad", method = RequestMethod.POST)
    public ModelAndView saveAd( ApartmentForm form, ShApartmentForm form2, BindingResult result){
    	System.out.println(form.getCategory());
    	if(form.getCategory().equals("Apartment")){
    		System.out.println("apartment cat");
    		sampleService.saveFrom(form);
    	}
    	if(form2.getCategory().equals("Shared Apartment")){
    		System.out.println("shared apartment cat");
    		sampleService.saveFrom(form2);
    	}
    	ModelAndView model= new ModelAndView("show");
    	return model;
    }*/
    

    @RequestMapping(value="/makeAd", method = RequestMethod.POST)
    public ModelAndView makeAd(ApartmentForm form, ShApartmentForm form2, BindingResult result){
    	System.out.println(form.getCategory());
    	ModelAndView model = null;
    	if(form.getCategory().equals("Apartment")){
    		System.out.println("apartment cat");    	
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
                	System.out.print("catch");
                	model = new ModelAndView("main");
                	model.addObject("page_error", e.getMessage());
                }
            } else {
            	System.out.println("else");
            	model = new ModelAndView("main");
            }
    		
    	}
    	return model;
    	
/*    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	sampleService.saveFrom(form);
            	model = new ModelAndView("viewAd");
                model.addObject("message","This is what your ad will look like:");
                form.setDescription(form.getDescription().replace("\n", "<br />\n"));
                model.addObject("apartmentForm", form);
            } catch (InvalidDateException e) {
            	model = new ModelAndView("newAd");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("newAd");
        }   	
    	return model;*/
    }
    
/*    @RequestMapping(value = "/new-shad", method = RequestMethod.GET)
    public ModelAndView newAdShAd(){
    	ModelAndView model = new ModelAndView("newAdShAp");
    	
    	model.addObject("apartmentForm", new ShApartmentForm());
    	
    	return model;
    }
    
    @RequestMapping(value="/makeAdShAp", method = RequestMethod.POST)
    public ModelAndView makeAdShAp(@Valid ShApartmentForm apartmentForm, BindingResult result){
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	//sampleService.saveFrom(apartmentForm);
            	model = new ModelAndView("viewAd");
                model.addObject("message","This is what your ad will look like:");
                apartmentForm.setDescription(apartmentForm.getDescription().replace("\n", "<br />\n"));
                model.addObject("apartmentForm", apartmentForm);
            } catch (InvalidDateException e) {
            	model = new ModelAndView("newAdShAp");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("newAdShAp");
        }   	
    	return model;
    }*/
    
    @RequestMapping(value="/editAd", method = RequestMethod.POST)
    public ModelAndView editAd(ApartmentForm apartmentForm, BindingResult result){
    	ModelAndView model;
    	
    	if (!result.hasErrors()) {
            model = new ModelAndView("newAd");
            if(apartmentForm.getCategory().equals("Apartment")){
            	Apartment oldAd = sampleService.getAd(apartmentForm.getId());
                apartmentForm.setDescription(oldAd.getDescription());
                apartmentForm.setFixedMoveIn(oldAd.isFixedMoveIn());
                apartmentForm.setFixedMoveOut(oldAd.isFixedMoveOut());
                model.addObject("oldAd", oldAd);
                model.addObject("apForm", apartmentForm);//mg
                model.addObject("shApForm", new ShApartmentForm());
            }
            else{
            	ShApartment oldAd = sampleService.getShApAd(apartmentForm.getId());
            	ShApartmentForm shApForm =  new ShApartmentForm();
            	shApForm.setDescription(oldAd.getDescription());
            	shApForm.setFixedMoveIn(oldAd.isFixedMoveIn());
            	shApForm.setFixedMoveOut(oldAd.isFixedMoveOut());
            	model.addObject("oldAd", oldAd);
            	model.addObject("apForm", apartmentForm);
                model.addObject("shApForm", shApForm);
            }
        } 
    	else {
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request) {
        ModelAndView model;
        if(request.isUserInRole("ROLE_USER")) {
            return "redirect:/editProfile";
        } else if(request.isUserInRole("ROLE_NEW_USER")) {
            return "redirect:/newProfile";
        } else {
            return "redirect:/";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView editProfile() {
        ModelAndView model = new ModelAndView("profile");
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("profileForm", new ProfileForm());
        model.addObject("user",sampleService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }

    @PreAuthorize("hasRole('ROLE_NEW_USER')")
    @RequestMapping(value = "/newProfile", method = RequestMethod.GET)
    public ModelAndView newProfile() {
        ModelAndView model = new ModelAndView();
        SecurityContext ctx = SecurityContextHolder.getContext();
        model.addObject("profileForm", new ProfileForm());
        model.addObject("user",sampleService.loadUserByEmail(ctx.getAuthentication().getName()));
        return model;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult result, RedirectAttributes redirectAttributes) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        profileForm.setEmail(ctx.getAuthentication().getName());
        sampleService.saveFrom(profileForm);
        redirectAttributes.addFlashAttribute("Profile saved.");
        return "redirect:/profile";
    }

    @PreAuthorize("hasRole('ROLE_NEW_USER')")
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



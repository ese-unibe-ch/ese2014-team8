package org.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ApartmentForm;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.pojos.ShApartmentForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamCreationForm;
import org.sample.controller.service.SampleService;
import org.sample.model.Apartment;
import org.sample.model.RealEstate;
import org.sample.model.ShApartment;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

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
    	ModelAndView model = new ModelAndView("index");

    	model.addObject("signupForm", new SignupForm());
        model.addObject("teams", sampleService.getAllTeams());
        return model;
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
    }
    
    @RequestMapping(value="/editAd", method = RequestMethod.POST)
    public ModelAndView editAd(ApartmentForm apartmentForm, ShApartmentForm shApartmentForm, BindingResult result){
    	ModelAndView model;
    	
    	if (!result.hasErrors()) {
            model = new ModelAndView("newAd");
            if(apartmentForm.getCategory().equals("Apartment")){
            	System.out.println("edit Post, Apartment");
            	Apartment oldAd = sampleService.getAd(apartmentForm.getId());
                apartmentForm.setDescription(oldAd.getDescription());
                apartmentForm.setFixedMoveIn(oldAd.isFixedMoveIn());
                apartmentForm.setFixedMoveOut(oldAd.isFixedMoveOut());
                apartmentForm.setNumberOfRooms(oldAd.getNumberOfRooms());
                apartmentForm.setSize(oldAd.getSize());
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
            	model.addObject("cate","Shared Apartment");
            	model.addObject("oldAd", oldAd);
                model.addObject("shApForm", shApartmentForm);
                model.addObject("apForm", new ApartmentForm());
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



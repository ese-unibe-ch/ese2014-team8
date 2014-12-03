package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.sample.controller.exceptions.*;
import org.sample.controller.pojos.*;
import org.sample.controller.service.*;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class AdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    @Autowired 
    RoomMateService roomMateService;
    
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		timeFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, "time", new CustomDateEditor(timeFormat, true));
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
    	return model;
    }

    @RequestMapping(value="/newSharedAd", method = RequestMethod.GET) 
    public Object makeSharedAd(HttpServletRequest request){
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	ModelAndView model = new ModelAndView("newSharedAd");
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("shApForm", new ShApartmentForm());
    	return model;
    }
    
 
    @RequestMapping(value="/viewAd", method = RequestMethod.POST)
    public Object makeAd(HttpServletRequest request, ApartmentForm form, ShApartmentForm form2, BindingResult result){
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(user.getIsNew()) {
            return "redirect:/profile";
        }
        
    	ModelAndView model = null;
    	if(form.getCategory().equals("Apartment")){   	
        	if (!result.hasErrors()) {
                try {
                    form.setUser(user);
                    Apartment apartment=adService.saveFrom(form);
                    
                    List<MultipartFile> images = form.getAdImages();
                    int imageNumber = 0;
                    String directory = "ApartmentImages";
                    
                    if (null != images && images.size() > 0) {
                        for (MultipartFile file : images) {
                        	imageNumber ++;
                        	String name = Long.toString(apartment.getId()) + "_" + Integer.toString(imageNumber);
                        	String returnMessage = saveImage(file, name , directory);
                        	if(!returnMessage.equals("You successfully uploaded the image")
                        			&&!returnMessage.equals("You failed to upload the image because the file was empty.")){
                        		throw new InvalidImageException(returnMessage);
                        	}
                        }
                    }
                    apartment = adService.setImages(apartment, imageNumber);
                    
                	model = new ModelAndView("viewAd");
                    model.addObject("message","This is what your ad will look like:");
                    model.addObject("category","Apartment");
                    model.addObject("ad", apartment);
                    model.addObject("messageForm", new MessageForm());
                } catch (InvalidDateException | InvalidImageException e) {
                	
                	model = new ModelAndView("newAd");
                	model.addObject("page_error", e.getMessage());
                	model.addObject("user", user);
                	model.addObject("apForm", form);
                }
            } else {
            	model = new ModelAndView("newAd");
            	model.addObject("user", user);
            }   	
    	}
    	if(form2.getCategory().equals("Shared Apartment")){
        	if (!result.hasErrors()) {
                try {
                    form2.setUser(user);
                	ShApartment apartment=adService.saveFrom(form2);
                	List<MultipartFile> images = form2.getAdImages();
                    int imageNumber = 0;
                    String directory = "ShApartmentImages";
                    if (null != images && images.size() > 0) {
                        for (MultipartFile file : images) {
                        	imageNumber ++;
                        	String name = Long.toString(apartment.getId()) + "_" + Integer.toString(imageNumber);
                        	String returnMessage = saveImage(file, name , directory);
                        	if(!returnMessage.equals("You successfully uploaded the image")
                        			&&!returnMessage.equals("You failed to upload the image because the file was empty.")){
                        		throw new InvalidImageException(returnMessage);
                        	}
                        }
                    }
                    apartment = adService.setImages(apartment, imageNumber);
                	if(form2.isAddRoomMate()==true){
                		return "redirect:/RoomMates/" + Long.toString(apartment.getId());
                	}
                	model = new ModelAndView("viewAd");
                    model.addObject("message","This is what your ad will look like:");
                    model.addObject("category","Shared Apartment");
                    model.addObject("ad", apartment);
                    model.addObject("messageForm", new MessageForm());
                } catch (InvalidDateException | InvalidImageException e) {
                	model = new ModelAndView("newSharedAd");
                	model.addObject("page_error", e.getMessage());
                	model.addObject("user", user);
                	model.addObject("apForm", form2);
                }
            } else {
            	model = new ModelAndView("main");
            }
    		
    	}
    	model.addObject("user",user);
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
            	Apartment oldAd = adService.getApAd(apartmentForm.getId());
            	apartmentForm = adService.fillInFormFrom(oldAd);
                model.addObject("category","Apartment");
                model.addObject("apForm", apartmentForm);
            }
            else{
            	ShApartment oldAd = adService.getShApAd(shApartmentForm.getId());
            	shApartmentForm = adService.fillInFormFrom(oldAd);
            	model.addObject("category","Shared Apartment");
                model.addObject("shApForm", shApartmentForm);
                model.addObject("roomMates", roomMateService.getRoomMates(shApartmentForm.getId()));
            }
        } 
    	else {
        	model = new ModelAndView("index");
        }   	
    	model.addObject("user",userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addObject("apForm", new ApartmentForm());
    	return model;
    }

    @RequestMapping(value="/editAd/{adCategory}/{adId}")
    public Object editAdId(HttpServletRequest request, @PathVariable String adCategory, @PathVariable Long adId) {
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("editAd");
        model.addObject("category",adCategory);
        model.addObject("user",user);
        if(adCategory.equals("Apartment") && (user.getIsAdmin() || user.getId() == adService.getApAd(adId).getOwner().getId())) {
            model.addObject("apForm", adService.fillInFormFrom(adService.getApAd(adId)));
        } else if(adCategory.equals("Shared Apartment") && (user.getIsAdmin() || user.getId() == adService.getShApAd(adId).getOwner().getId())) {
            model.addObject("shApForm", adService.fillInFormFrom(adService.getShApAd(adId)));
            model.addObject("roomMates", roomMateService.getRoomMates(adId));
        }
        return model;
    }
    
    @RequestMapping(value="/viewAd/{category}/{adId}")
    public Object viewAdId(HttpServletRequest request, @PathVariable String category, @PathVariable Long adId) {
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("viewAd");
        model.addObject("category", category);
        model.addObject("message", "This is what your ad will look like:");
        model.addObject("messageForm", new MessageForm());
        model.addObject("user", user);
        model.addObject("ad", adService.getAd(category, adId));
        model.addObject("apartmentForm", new ApartmentForm());
            
        return model;
    }
    
    @RequestMapping(value="/removeAd/{category}/{adId}")
    public Object removeAd(HttpServletRequest request, @PathVariable String category, @PathVariable Long adId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
             return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
             return "redirect:/profile";
        }
        adService.deleteAd(category, adId);
    	return "redirect:/placedAds";
    }
    
    
    private String saveImage(MultipartFile file, String name, String directory) {
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator +".."+ File.separator+"src" + File.separator +"main" + File.separator + "webapp" + File.separator + directory);
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                if(extension.equals("jpg") || extension.equals("jpeg") ){
                	File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".jpg" );
                	BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                	stream.write(bytes);
                	stream.close();
 
                	LOGGER.info("Server File Location=" + serverFile.getAbsolutePath());
 
                	return "You successfully uploaded the image" ;
                }
                else{
                	return "Image must be jpg format";
                }
                
            } catch (Exception e) {
                return "You failed to upload the image => " + e.getMessage();
            }
        } else {
            return "You failed to upload the image because the file was empty.";
        }
	}

}



package org.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ProfileController extends ImageController{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request) {
       
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
        User user = userService.loadUserByEmail(userMail);
        model.addObject("profileForm", userService.fillProfileForm(user));
        model.addObject("user", user);
        model.addObject("profile", user);
       
        return model;
    }

    @RequestMapping(value = "/editProfile/{profileId}", method = RequestMethod.GET)
    public Object editProfileId(HttpServletRequest request, @PathVariable Long profileId) {
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User profile = userService.getUser(profileId);
        if(!request.isUserInRole("ROLE_PERSONA_USER") || !user.getIsAdmin()) {
            return "redirect:/";
        } else if(user.getIsNew()) {
            return "redirect:/profile";
        }
        ModelAndView model = new ModelAndView("profile");
        model.addObject("profileForm", userService.fillProfileForm(profile));
        model.addObject("user", user);
        model.addObject("profile", profile);
        model.addObject("apartments",adService.getApartmentsByUser(user.getEmail()));
        model.addObject("shApartments",adService.getShApartmentsByUser(user.getEmail()));
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
        System.out.println(profileForm.getFirstName());
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
    
    @RequestMapping(value="/showProfile/{personId}", method = RequestMethod.GET)
    public Object showProfile(HttpServletRequest request, @PathVariable("personId") Long pId){
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
    		return "redirect:/";
    	} else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	ModelAndView model = new ModelAndView("showProfile");
    	model.addObject("profile", userService.getPerson(pId));
    	model.addObject("user", user);
    	return model;
    }
    
    
    @RequestMapping(value = "/profileImage", method = RequestMethod.POST)
    public Object profileImgUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(user.getIsNew()) {
            return "redirect:/profile";
        }
        String message =  saveImage(file, Long.toString(user.getPerson().getId()), "profileImg");
        if(message.equals("You successfully uploaded the image")){
        	user = userService.imageSaved(user);
        }
    	ModelAndView model = new ModelAndView("profile");
        model.addObject("profileForm", userService.fillProfileForm(user));
        model.addObject("user", user);
        model.addObject("profile", user);
        model.addObject("message", message);
        return model;
    }
    
    @RequestMapping(value = "/removeProfileImage/{personId}", method = RequestMethod.GET)
    Object upload(HttpServletRequest request, @PathVariable Long personId){
    	User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(user.getIsNew()) {
            return "redirect:/profile";
        }
    	
    	userService.removeImage(personId);
    	
    	return "redirect:/editProfile";
    }

	
}



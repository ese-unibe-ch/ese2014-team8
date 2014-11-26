package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.IOUtils;

@Controller
public class ProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    AdService adService;
    @Autowired
    UserService userService;
    
    
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
        User user = userService.loadUserByEmail(userMail);
        model.addObject("profileForm", userService.fillProfileForm(user));
        model.addObject("user", user);
        model.addObject("profile", user);
        model.addObject("apartments",adService.getApartmentsByUser(user.getEmail()));
        model.addObject("shApartments",adService.getShApartmentsByUser(user.getEmail()));
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

    @RequestMapping(value = "/saveUserPicture", method = RequestMethod.POST)
    public Object saveUserPicture(HttpServletRequest request, @RequestParam("picture") MultipartFile picture) {
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        }
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!picture.isEmpty()) {
            try {
                byte[] bytes = picture.getBytes();
                user.setPicture(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return "redirect:/profile";
            }
        }
        return "redirect:/profile";
    }

    @RequestMapping(value = "/getUserPicture/{uid}")
    public void getUserPicture(HttpServletResponse response , @PathVariable("uid") Long uid) throws IOException {
        response.setContentType("image/jpeg");
        User profile = userService.getUser(uid);
        if(profile.getPicture() == null) {
            return;
        }
        InputStream in = new ByteArrayInputStream(profile.getPicture());
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int count = 0;
        int n = 0;
        while (-1 != (n = in.read(buffer))) {
            out.write(buffer, 0, n);
            count += n;
        }
        out.flush();
        out.close();
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do not have permission to do that!");
        return "redirect:/";
    }

}



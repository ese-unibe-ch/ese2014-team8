package org.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.sample.controller.pojos.MessageForm;
import org.sample.controller.service.BookMarkService;
import org.sample.controller.service.UserService;
import org.sample.model.RealEstate;
import org.sample.model.User;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookMarkController {
	
    @Autowired
    UserService userService;
    @Autowired
    BookMarkService bookMarkService;
	
    @RequestMapping(value="/addBookMark/{category}/{adId}",	method=RequestMethod.GET)
    public	Object displayAd(HttpServletRequest request, @PathVariable Long adId, @PathVariable	String	category)	{
        if(!request.isUserInRole("ROLE_PERSONA_USER")) {
            return "redirect:/";
        } else if(userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getIsNew()) {
            return "redirect:/profile";
        }
        User user = userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	bookMarkService.setBookMark(user, category, adId);
    	return  "redirect:/searchresults/"+category+"/"+adId;
    }
	

}


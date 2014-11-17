package org.sample.controller.service;

import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

/**
 * Created by zilti on 05.11.14.
 */
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService, Serializable {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.loadUserByEmail(s);
        if(user == null) {
            throw new UsernameNotFoundException("User not registered");
        }
        return userService.loadUserByEmail(s);
    }
}

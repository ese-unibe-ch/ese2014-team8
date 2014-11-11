package org.sample.model;

import me.sniggle.springframework.security.persona.common.PersonaSignUpHandler;
import org.sample.controller.service.SampleService;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by zilti on 24.10.14.
 */
public class PersonaSignupHandler implements PersonaSignUpHandler<User>, Serializable {

    @Autowired
    SampleService sampleService;

    @Autowired
    UserDao userDao;

    public PersonaSignupHandler() {
        super();
    }

    @Override
    public User createUser(final String email) {
        User user = new User();
        user.setEmail(email);
        user.setAuthorities(Arrays.asList(new Team8Authority("ROLE_PERSONA_USER")));
        user.setIsNew(true);
        userDao.save(user);
        return user;
    }
}

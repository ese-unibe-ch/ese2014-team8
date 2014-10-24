package org.sample.model;

import me.sniggle.springframework.security.persona.common.PersonaSignUpHandler;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by zilti on 24.10.14.
 */
public class PersonaSignupHandler implements PersonaSignUpHandler<User>, Serializable {

    public PersonaSignupHandler() {
        super();
    }

    @Override
    public User createUser(final String email) {
        User user = new User();
        user.setEmail(email);
        user.setAuthorities(Arrays.asList(() -> "ROLE_USER"));
        return user;
    }
}

package me.sniggle.springframework.security.persona.common;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * This interface can be used in order to handle various sign up operations to
 * create a new user account
 * 
 * @author iulius
 * 
 */
public interface PersonaSignUpHandler<T extends UserDetails> {

  /**
   * creates a new user using the underlying persistence layer
   * 
   * @param email
   *          the email address to be used
   * @return the user details
   */
  public abstract T createUser(final String email);

}

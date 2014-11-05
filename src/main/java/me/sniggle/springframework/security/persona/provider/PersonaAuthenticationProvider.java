package me.sniggle.springframework.security.persona.provider;

import me.sniggle.springframework.security.persona.common.PersonaSignUpHandler;
import me.sniggle.springframework.security.persona.common.PersonaVerificationHandler;
import me.sniggle.springframework.security.persona.common.PersonaVerificationResponse;
import me.sniggle.springframework.security.persona.common.SuccessPersonaVerificationResponse;
import me.sniggle.springframework.security.persona.common.impl.GenericPersonaVerificationResponse.PersonaVerificationStatus;
import me.sniggle.springframework.security.persona.token.PersonaAuthenticationToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * The AuthenticationProvider implementation used to authenticate against the
 * Persona service
 * 
 * @author iulius
 * 
 */
public class PersonaAuthenticationProvider<T extends UserDetails> implements
    AuthenticationProvider, InitializingBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonaAuthenticationProvider.class);

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private PersonaVerificationHandler personaVerificationHandler;
  @Autowired
  private PersonaSignUpHandler<T> personaSignUpHandler;
  private boolean signUpAutomatically = true;

  /**
   * 
   */
  public PersonaAuthenticationProvider() {
    super();
  }

  /**
   * 
   * @param userDetailsService
   * @param personcaVerificationHandler
   * @param personaSignUpHandler
   */
  public PersonaAuthenticationProvider(UserDetailsService userDetailsService,
      PersonaVerificationHandler personcaVerificationHandler,
      PersonaSignUpHandler<T> personaSignUpHandler) {
    super();
    this.userDetailsService = userDetailsService;
    this.personaVerificationHandler = personcaVerificationHandler;
    this.personaSignUpHandler = personaSignUpHandler;
  }

  /**
   * @return the signUpAutomatically
   */
  public boolean isSignUpAutomatically() {
    return signUpAutomatically;
  }

  /**
   * @param signUpAutomatically
   *          the signUpAutomatically to set
   */
  public void setSignUpAutomatically(boolean signUpAutomatically) {
    this.signUpAutomatically = signUpAutomatically;
  }

  /**
   * @return the userDetailsService
   */
  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

  /**
   * @param userDetailsService
   *          the userDetailsService to set
   */
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * @return the personaVerificationHandler
   */
  public PersonaVerificationHandler getPersonaVerificationHandler() {
    return personaVerificationHandler;
  }

  /**
   * @param personaVerificationHandler
   *          the personaVerificationHandler to set
   */
  public void setPersonaVerificationHandler(PersonaVerificationHandler personaVerificationHandler) {
    this.personaVerificationHandler = personaVerificationHandler;
  }

  /**
   * @return the personaSignUpHandler
   */
  public PersonaSignUpHandler<T> getPersonaSignUpHandler() {
    return personaSignUpHandler;
  }

  /**
   * @param personaSignUpHandler
   *          the personaSignUpHandler to set
   */
  public void setPersonaSignUpHandler(PersonaSignUpHandler<T> personaSignUpHandler) {
    this.personaSignUpHandler = personaSignUpHandler;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.authentication.AuthenticationProvider#authenticate
   * (org.springframework.security.core.Authentication)
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (authentication != null && authentication instanceof PersonaAuthenticationToken) {
      final PersonaAuthenticationToken personaAuthenticationToken = (PersonaAuthenticationToken) authentication;
      if (personaAuthenticationToken.getCredentials() == null
          || !(personaAuthenticationToken.getCredentials() instanceof CharSequence)
          || personaAuthenticationToken.getCredentials() instanceof CharSequence
          && !StringUtils.hasText((CharSequence) personaAuthenticationToken.getCredentials())) {
        throw new AuthenticationCredentialsNotFoundException("Missing Mozilla Persona assertion");
      }
      CharSequence assertion = (CharSequence) personaAuthenticationToken.getCredentials();
      PersonaVerificationResponse verificationResponse = personaVerificationHandler
          .verify(assertion);
      if (verificationResponse == null
          || verificationResponse.getStatus() == PersonaVerificationStatus.FAILED) {
        throw new AuthenticationServiceException("The assertion was not verified! "
            + (verificationResponse == null ? "No response received"
                : verificationResponse.getReason()));
      } else {
        SuccessPersonaVerificationResponse successPersonaVerificationResponse = (SuccessPersonaVerificationResponse) verificationResponse;
        UserDetails user = getUserOrCreate(successPersonaVerificationResponse.getEmail());
        if (user == null) {
          throw new UsernameNotFoundException("");
        }
        return new PersonaAuthenticationToken(
               // getUserOrCreate(successPersonaVerificationResponse.getEmail()));
            userDetailsService.loadUserByUsername(successPersonaVerificationResponse.getEmail()
                .toString()));
      }
    }
    throw new InternalAuthenticationServiceException("No valid authentication provided");
  }

  /**
   * returns a user if one exists or creates a new one if configured
   * appropriately
   * 
   * @param email
   *          the email address of the user
   * @throws UsernameNotFoundException
   *           if none is found and the automatic sign up is disabled
   * @return the user details belonging to the email address
   */
  protected UserDetails getUserOrCreate(CharSequence email) {
    UserDetails result = null;
    if (email != null) {
      try {
        result = userDetailsService.loadUserByUsername(email.toString());
      } catch (UsernameNotFoundException e) {
        result = handleUsernameNotFound(email, e);
      }
    }
    return result;
  }

  private UserDetails handleUsernameNotFound(CharSequence email, UsernameNotFoundException e) {
    UserDetails result = null;
    if (isSignUpAutomatically()) {
      LOGGER.info("Automatically signing up user with email [{}]", email);
      result = personaSignUpHandler.createUser(email.toString());
      if (result != null) {
        LOGGER.info("User with email [{}] signed up automatically", email);
      } else {
        LOGGER.error("Failed to create user account for email [{}]", email);
      }
    } else {
      LOGGER.info(e.getMessage());
      throw e;
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.authentication.AuthenticationProvider#supports
   * (java.lang.Class)
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return PersonaAuthenticationToken.class.isAssignableFrom(authentication);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(userDetailsService, "The user details service must be configured");
    Assert.notNull(personaVerificationHandler,
        "The Persona verification handler must be configured");
    Assert.notNull(personaSignUpHandler, "The Persona sign up handler must be configured");
  }

}

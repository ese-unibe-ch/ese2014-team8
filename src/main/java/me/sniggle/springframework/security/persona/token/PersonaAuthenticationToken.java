package me.sniggle.springframework.security.persona.token;

import static java.util.Arrays.asList;

import java.util.Collection;

import me.sniggle.springframework.security.persona.authorities.PersonaUserAuthority;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * The Persona authentication token
 * 
 * @author iulius
 * 
 */
public class PersonaAuthenticationToken extends AbstractAuthenticationToken {

  /**
	 * 
	 */
  private static final long serialVersionUID = -7992375394337724084L;
  private CharSequence assertion;
  private Object principal;

  /**
   * 
   * @param assertion
   */
  public PersonaAuthenticationToken(CharSequence assertion) {
    this(assertion, asList(new PersonaUserAuthority()));
  }

  /**
   * @param assertion
   * @param authorities
   */
  public PersonaAuthenticationToken(CharSequence assertion,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.assertion = assertion;
    setAuthenticated(false);
  }

  /**
   * 
   * @param principal
   */
  public PersonaAuthenticationToken(Object principal) {
    this(principal, asList(new PersonaUserAuthority()));
  }

  /**
   * 
   * @param principal
   * @param authorities
   */
  public PersonaAuthenticationToken(Object principal,
      Collection<? extends GrantedAuthority> authorities) {
    super(asList(new PersonaUserAuthority()));
    this.principal = principal;
    setAuthenticated(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.Authentication#getCredentials()
   */
  @Override
  public Object getCredentials() {
    return assertion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.Authentication#getPrincipal()
   */
  @Override
  public Object getPrincipal() {
    return principal;
  }

}

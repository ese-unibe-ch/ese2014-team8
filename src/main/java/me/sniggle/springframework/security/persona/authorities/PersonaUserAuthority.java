package me.sniggle.springframework.security.persona.authorities;

import org.springframework.security.core.GrantedAuthority;

/**
 * The default persona authority
 * 
 * @author iulius
 * 
 */
public class PersonaUserAuthority implements GrantedAuthority {

  /**
	 * 
	 */
  private static final long serialVersionUID = -542131792787742109L;

  /**
	 * 
	 */
  public PersonaUserAuthority() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.GrantedAuthority#getAuthority()
   */
  @Override
  public String getAuthority() {
    return "ROLE_PERSONA_USER";
  }

}

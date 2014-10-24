package me.sniggle.springframework.security.persona.filter;

import me.sniggle.springframework.security.persona.handler.PersonaLogoutSuccessHandler;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * @author iulius
 *
 */
public class PersonaLogoutFilter extends LogoutFilter {

  private static final String DEFAULT_LOGOUT_URL = "/auth/logout";

  /**
   * this warning is acceptable as the appropriate class to use is set to private in the LogoutFilter
   */
  @SuppressWarnings("deprecation")
  public PersonaLogoutFilter() {
    super(new PersonaLogoutSuccessHandler(), new SecurityContextLogoutHandler());
    setFilterProcessesUrl(DEFAULT_LOGOUT_URL);
  }
}

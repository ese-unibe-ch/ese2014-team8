package me.sniggle.springframework.security.persona.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * @author iulius
 *
 */
public class PersonaLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

  /**
   * 
   */
  public PersonaLogoutSuccessHandler() {
    super();
  }

  /*
   * simply return HTTP code 200 to signal the successful authorization
   * 
   * (non-Javadoc)
   * 
   * @see org.springframework.security.web.authentication.
   * AbstractAuthenticationTargetUrlRequestHandler
   * #handle(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse,
   * org.springframework.security.core.Authentication)
   */
  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.flushBuffer();
  }

}

package me.sniggle.springframework.security.persona;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * The default entry point for Persona authentication
 * 
 * @author iulius
 * 
 */
public class PersonaAuthenticationEntryPoint implements
    AuthenticationEntryPoint {

  private boolean forceHttps = true;

  /**
	 * 
	 */
  public PersonaAuthenticationEntryPoint() {
    super();
  }

  /**
   * @return the forceHttps
   */
  public boolean isForceHttps() {
    return forceHttps;
  }

  /**
   * @param forceHttps
   *          the forceHttps to set
   */
  public void setForceHttps(boolean forceHttps) {
    this.forceHttps = forceHttps;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.web.AuthenticationEntryPoint#commence(javax
   * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
   * org.springframework.security.core.AuthenticationException)
   */
  @Override
  public void commence(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    if (isForceHttps() && !"https".equals(request.getScheme())) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } else {
      response.setStatus(HttpServletResponse.SC_OK);
    }
    response.flushBuffer();
  }
}

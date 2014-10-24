package me.sniggle.springframework.security.persona.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * The appropriate handler to verify the successful authorization
 * 
 * @author iulius
 */
public class PersonaAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  public PersonaAuthenticationSuccessHandler() {
    super();
  }

  /**
   * method to apply custom extensions to the request or response
   * @param req
   *  the HTTP request used for authorization
   * @param res
   *  the HTTP response to indicate authorization result 
   * @param authentication
   *  the authentication token
   * @throws IOException thrown if handling of request or response is erroneous
   */
  protected void handleAuthenticationResponse(HttpServletRequest req, HttpServletResponse res,
      Authentication authentication) throws IOException {
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
    handleAuthenticationResponse(request, response, authentication);
    response.flushBuffer();
  }

}

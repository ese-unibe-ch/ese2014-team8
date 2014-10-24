package me.sniggle.springframework.security.persona.filter;

import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sniggle.springframework.security.persona.handler.PersonaAuthenticationSuccessHandler;
import me.sniggle.springframework.security.persona.token.PersonaAuthenticationToken;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * The authentication processing filter actually performing the verification and
 * authentication
 * 
 * @author iulius
 * 
 */
public class PersonaAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

  private static final String DEFAULT_PROCESSING_URL = "/auth/login";
  private static final String ASSERTION_PARAM_NAME = "assertion";

  protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

  public PersonaAuthenticationProcessingFilter() {
    super(DEFAULT_PROCESSING_URL);
    setAuthenticationSuccessHandler(new PersonaAuthenticationSuccessHandler());
    setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler());
  }

  protected String obtainAssertion(HttpServletRequest request) {
    return obtainAssertion(request, ASSERTION_PARAM_NAME);
  }

  protected String obtainAssertion(HttpServletRequest request, String paramName) {
    return request.getParameter(paramName);
  }

  private void setDetails(HttpServletRequest request, PersonaAuthenticationToken authRequest) {
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.web.authentication.
   * AbstractAuthenticationProcessingFilter
   * #attemptAuthentication(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse)
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    RuntimeException ex = null;
    try {
      if ("POST".equals(request.getMethod())) {
        String assertion = obtainAssertion(request);
        if (hasText(assertion)) {
          PersonaAuthenticationToken authRequest = new PersonaAuthenticationToken(assertion);
          setDetails(request, authRequest);
          return getAuthenticationManager().authenticate(authRequest);
        } else {
          ex = new AuthenticationServiceException("No assertion provided in authentication request");
        }
      } else {
        ex = new AuthenticationServiceException(
            "Only a post request is allowed for persona authentication verification!");
      }
    } catch (RuntimeException e) {
      ex = new InternalAuthenticationServiceException("Error authenticating with Mozilla Persona",
          e);
    }
    throw ex;
  }

}

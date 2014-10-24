package me.sniggle.springframework.security.persona.common;

import me.sniggle.springframework.security.persona.common.impl.GenericPersonaVerificationResponse.PersonaVerificationStatus;

/**
 * The base interface to group all verification response
 * 
 * @author iulius
 * 
 */
public interface PersonaVerificationResponse {

  /**
   * 
   * @return the status of the response
   */
  public abstract PersonaVerificationStatus getStatus();

  /**
   * 
   * @return the reason for the response as provided by the verification process
   */
  public abstract CharSequence getReason();

}
package me.sniggle.springframework.security.persona.common;

import java.util.Date;

/**
 * Extended interface to represent a successful verification
 * 
 * @author iulius
 * 
 */
public interface SuccessPersonaVerificationResponse extends
    PersonaVerificationResponse {

  /**
   * @return the email that was verified
   */
  public abstract CharSequence getEmail();

  /**
   * @return the expires the date when this verification expires
   */
  public abstract Date getExpires();

  /**
   * @return the issuer the issuer of the verification
   */
  public abstract CharSequence getIssuer();

  /**
   * @return the audience, e.g. your server <b>https://www.example.org</b>
   */
  public abstract CharSequence getAudience();

}
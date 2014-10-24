package me.sniggle.springframework.security.persona.common;

/**
 * This interface encapsulates all methods required to verify an assertion
 * provided by the login call
 * 
 * @author iulius
 * 
 */
public interface PersonaVerificationHandler {

  /**
   * Verifies that the provided assertion is correct
   * 
   * @param assertion
   *          the assertion to be verified
   * @return the result of the verification process
   */
  public abstract PersonaVerificationResponse verify(CharSequence assertion);
}

package me.sniggle.springframework.security.persona.common.impl;

import java.io.Serializable;
import java.util.Date;

import me.sniggle.springframework.security.persona.common.PersonaVerificationResponse;
import me.sniggle.springframework.security.persona.common.SuccessPersonaVerificationResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * The generic encapsulation of a successful or failed verification of a Persona
 * authentication request
 * 
 * @author iulius
 * 
 */
public class GenericPersonaVerificationResponse implements PersonaVerificationResponse,
    Serializable, SuccessPersonaVerificationResponse {

  /**
   * 
   */
  private static final long serialVersionUID = -6830999914058000018L;

  /**
   * The status' defined for the verification
   * 
   * @author iulius
   * 
   */
  public enum PersonaVerificationStatus {
    OK("okay"), FAILED("failure");

    /**
     * the value as retrieved from the response
     */
    private String status;

    private PersonaVerificationStatus(String status) {
      this.status = status;
    }

    @JsonValue
    public String status() {
      return status;
    }

    /**
     * simple factory function to create the enumerated value
     * 
     * @param status
     *          the status as string as retrieved from the verification response
     *          data
     * @return the appropriate enumerated status value or null if the status
     *         does not match any enumerated value
     */
    @JsonCreator
    public static PersonaVerificationStatus forStatus(String status) {
      PersonaVerificationStatus result = null;
      for (PersonaVerificationStatus s : values()) {
        if (s.status().equals(status)) {
          result = s;
          break;
        }
      }
      return result;
    }
  }

  @JsonProperty
  @JsonDeserialize()
  private PersonaVerificationStatus status;
  @JsonProperty
  private CharSequence reason;
  @JsonProperty
  private CharSequence email;
  @JsonProperty
  private Date expires;
  @JsonProperty
  private CharSequence issuer;
  @JsonProperty
  private CharSequence audience;

  /**
   * 
   */
  public GenericPersonaVerificationResponse() {
    super();
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(PersonaVerificationStatus status) {
    this.status = status;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * me.sniggle.springframework.security.persona.common.PersonaVerificationResponse
   * #getStatus()
   */
  @Override
  public PersonaVerificationStatus getStatus() {
    return status;
  }

  /**
   * @param reason
   *          the reason to set
   */
  public void setReason(CharSequence reason) {
    this.reason = reason;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * me.sniggle.springframework.security.persona.common.PersonaVerificationResponse
   * #getReason()
   */
  @Override
  public CharSequence getReason() {
    return reason;
  }

  /*
   * (non-Javadoc)
   * 
   * @see me.sniggle.springframework.security.persona.common.impl.
   * SuccessPersonaVerificationResponse#getEmail()
   */
  @Override
  public CharSequence getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(CharSequence email) {
    this.email = email;
  }

  /*
   * (non-Javadoc)
   * 
   * @see me.sniggle.springframework.security.persona.common.impl.
   * SuccessPersonaVerificationResponse#getExpires()
   */
  @Override
  public Date getExpires() {
    return expires;
  }

  /**
   * @param expires
   *          the expires to set
   */
  public void setExpires(Date expires) {
    this.expires = expires;
  }

  /*
   * (non-Javadoc)
   * 
   * @see me.sniggle.springframework.security.persona.common.impl.
   * SuccessPersonaVerificationResponse#getIssuer()
   */
  @Override
  public CharSequence getIssuer() {
    return issuer;
  }

  /**
   * @param issuer
   *          the issuer to set
   */
  public void setIssuer(CharSequence issuer) {
    this.issuer = issuer;
  }

  /*
   * (non-Javadoc)
   * 
   * @see me.sniggle.springframework.security.persona.common.impl.
   * SuccessPersonaVerificationResponse#getAudience()
   */
  @Override
  public CharSequence getAudience() {
    return audience;
  }

  /**
   * @param audience
   *          the audience to set
   */
  public void setAudience(CharSequence audience) {
    this.audience = audience;
  }

}

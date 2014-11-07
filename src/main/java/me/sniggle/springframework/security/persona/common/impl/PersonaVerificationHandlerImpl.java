package me.sniggle.springframework.security.persona.common.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import me.sniggle.springframework.security.persona.common.PersonaVerificationHandler;
import me.sniggle.springframework.security.persona.common.PersonaVerificationResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The bean handling the verification requests
 * 
 * @author iulius
 * 
 */
public class PersonaVerificationHandlerImpl implements PersonaVerificationHandler, InitializingBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonaVerificationHandlerImpl.class);

  private HttpClient httpClient = HttpClientBuilder.create().build();
  private ObjectMapper objectMapper;
  private String verificationUrl = "https://verifier.login.persona.org/verify";
  private String audience;

  /**
   * default constructor
   */
  public PersonaVerificationHandlerImpl() {
    super();
  }

  /**
   * @return the httpClient
   */
  public HttpClient getHttpClient() {
    return httpClient;
  }

  /**
   * @param httpClient
   *          the httpClient to set
   */
  public void setHttpClient(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * @return the objectMapper
   */
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  /**
   * @param objectMapper
   *          the objectMapper to set
   */
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * @return the audience
   */
  public String getAudience() {
    return audience;
  }

  /**
   * @param audience
   *          the audience to set
   */
  public void setAudience(String audience) {
    this.audience = audience;
  }

  /**
   * @return the verificationUrl
   */
  public String getVerificationUrl() {
    return verificationUrl;
  }

  /**
   * @param verificationUrl
   *          the verificationUrl to set
   */
  public void setVerificationUrl(String verificationUrl) {
    this.verificationUrl = verificationUrl;
  }

  /**
   * creates the request entity to perform the verification
   * 
   * @param assertion
   *          the assertion to be verified
   * @return the appropriate HTTP entity
   * @throws UnsupportedEncodingException
   */
  private HttpEntity getRequestEntity(CharSequence assertion) throws UnsupportedEncodingException {
    LOGGER.debug("Create request entity");
    List<NameValuePair> parameters = new ArrayList<>();
    String assertionString;
    if (assertion != null) {
      if (assertion instanceof String) {
        assertionString = (String) assertion;
      } else {
        assertionString = assertion.toString();
      }
    } else {
      assertionString = null;
    }
    LOGGER.debug("Setting assertion parameter: [{}]", assertionString);
    LOGGER.debug("Setting audience parameter: [{}]", getAudience());
    parameters.add(new BasicNameValuePair("assertion", assertionString));
    parameters.add(new BasicNameValuePair("audience", getAudience()));
    return new UrlEncodedFormEntity(parameters);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * me.sniggle.springframework.security.persona.common.PersonaVerificationHandler
   * #verify(java.lang.CharSequence)
   */
  @Override
  public PersonaVerificationResponse verify(CharSequence assertion) {
    LOGGER.trace("verify(CharSequence)");
    LOGGER.debug("Provided assertion [{}]", assertion);
    GenericPersonaVerificationResponse verificationResponse = null;
    try {
      String verificationUrl = getVerificationUrl();
      LOGGER.debug("Verification URL being called [{}]", verificationUrl);
      HttpPost request = new HttpPost(verificationUrl);
      request.setEntity(getRequestEntity(assertion));
      HttpResponse httpResponse = httpClient.execute(request);
      HttpEntity responseEntity = httpResponse.getEntity();
      verificationResponse = objectMapper.readValue(responseEntity.getContent(),
          GenericPersonaVerificationResponse.class);
      LOGGER.info("Verification response status [{}]", verificationResponse.getStatus());
    } catch (IOException e) {
      LOGGER.error("Error during assertion verification! [{}]", e.getMessage());
    }
    return verificationResponse;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(httpClient, "The HTTP Client must be configured");
    Assert.notNull(objectMapper, "The ObjectMapper must be configured");
    Assert.hasText(audience, "The audience must be configured in the Spring Context");
  }

}

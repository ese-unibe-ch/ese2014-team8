package me.sniggle.springframework.security.persona.factory.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Handles the XML namespace
 * 
 * <pre>
 * http://www.sniggle.me/schema/spring/security
 * </pre>
 * 
 * in a Spring Security configuration context
 * 
 * @author iulius
 * 
 */
public class PersonaSecurityNamespaceHandler extends NamespaceHandlerSupport {

  /**
   * 
   */
  public PersonaSecurityNamespaceHandler() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
   */
  @Override
  public void init() {
    registerBeanDefinitionParser("personaAuthenticationConfiguration",
        new PersonaSecurityBeanDefinitionParser());
  }
}

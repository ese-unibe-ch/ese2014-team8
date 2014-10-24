package me.sniggle.springframework.security.persona.factory.xml;

import me.sniggle.springframework.security.persona.PersonaAuthenticationEntryPoint;
import me.sniggle.springframework.security.persona.common.PersonaVerificationHandler;
import me.sniggle.springframework.security.persona.common.impl.PersonaVerificationHandlerImpl;
import me.sniggle.springframework.security.persona.filter.PersonaAuthenticationProcessingFilter;
import me.sniggle.springframework.security.persona.filter.PersonaLogoutFilter;
import me.sniggle.springframework.security.persona.handler.PersonaAuthenticationSuccessHandler;
import me.sniggle.springframework.security.persona.provider.PersonaAuthenticationProvider;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Creates the appropriate {@link BeanDefinition}s to create a Mozilla Persona
 * setup using the personaAuthenticationConfiguration element from the
 * http://www.sniggle.me/schema/spring/security namespace
 * 
 * @author iulius
 * 
 */
public class PersonaSecurityBeanDefinitionParser extends AbstractBeanDefinitionParser {

  /**
   * Helper callback class to register the various beans in a unified way to the
   * {@link BeanDefinitionRegistry}
   * 
   * @author iulius
   * 
   */
  private abstract class BeanRegistrationCallback {

    private final CharSequence beanName;
    private final BeanDefinitionBuilder beanDefinitionBuilder;
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    /**
     * the constructor
     * 
     * @param beanName
     *          the bean identifier of the bean to be created
     * @param beanDefinitionBuilder
     *          the bean definition factory
     * @param beanDefinitionRegistry
     *          the target bean definition registry
     */
    protected BeanRegistrationCallback(String beanName,
        BeanDefinitionBuilder beanDefinitionBuilder, BeanDefinitionRegistry beanDefinitionRegistry) {
      super();
      this.beanName = beanName;
      this.beanDefinitionBuilder = beanDefinitionBuilder;
      this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * shall be used as helper method to pass constructor arguments and
     * references to the bean definition and may be overridden by anonymous
     * implementations if necessary
     */
    public void addConstructorArguments() {
    }

    /**
     * helper method which provides simplified access to beanDefinitionBuilder
     * of this bean
     * 
     * @param value
     *          the constructor value to be passed on
     */
    @SuppressWarnings("unused")
    public void addConstructorArgument(Object value) {
      beanDefinitionBuilder.addConstructorArgValue(value);
    }

    /**
     * helper method which provides simplified access to beanDefinitionBuilder
     * of this bean
     * 
     * @param beanName
     *          the name of the referenced bean
     */
    @SuppressWarnings("unused")
    public void addConstructorArgumentReference(String beanName) {
      beanDefinitionBuilder.addConstructorArgReference(beanName);
    }

    /**
     * helper method which provides simplified access to beanDefinitionBuilder
     * of this bean
     * 
     * @param name
     *          the name of the property
     * @param value
     *          the value to be set
     */
    public void addProperty(String name, Object value) {
      beanDefinitionBuilder.addPropertyValue(name, value);
    }

    /**
     * helper method which provides simplified access to beanDefinitionBuilder
     * of this bean
     * 
     * @param name
     *          the name of the property
     * @param beanName
     *          the name of the bean to be passed
     */
    public void addPropertyReference(String name, String beanName) {
      beanDefinitionBuilder.addPropertyReference(name, beanName);
    }

    /**
     * shall be used as helper method to pass property arguments and references
     * to the bean definition and may be overridden by anonymous implementations
     * if necessary
     */
    public void addProperties() {
    }

    /**
     * 
     * @return the target name of the bean
     */
    public CharSequence getBeanName() {
      return beanName;
    }

    /**
     * registers the {@link BeanDefinition} defined with
     * {@link #addConstructorArguments()} and {@link #addProperties()} with the
     * given beanName {@link #getBeanName()}
     */
    public void registerBean() {
      beanDefinitionRegistry.registerBeanDefinition(getBeanName().toString(),
          beanDefinitionBuilder.getBeanDefinition());
    }

  }

  /**
   * Helper callback class to create a RootBeanDefinition
   * 
   * @author iulius
   * 
   */
  private class RootBeanRegistrationCallback extends BeanRegistrationCallback {

    /**
     * the constructor
     * 
     * @param beanName
     *          the name of the bean
     * @param beanClass
     *          the type of the bean
     * @param beanDefinitionRegistry
     *          the bean definition registry
     */
    public RootBeanRegistrationCallback(String beanName, Class<?> beanClass,
        BeanDefinitionRegistry beanDefinitionRegistry) {
      super(beanName, BeanDefinitionBuilder.rootBeanDefinition(beanClass), beanDefinitionRegistry);
    }

  }

  /**
   * Helper callback class to create a GenericBeanDefinition
   * 
   * @author iulius
   * 
   */
  @SuppressWarnings("unused")
  private class GenericBeanRegistrationCallback extends BeanRegistrationCallback {

    /**
     * the constructor
     * 
     * @param beanName
     *          the name of the bean
     * @param beanClass
     *          the type of the bean
     * @param beanDefinitionRegistry
     *          the bean definition registry
     */
    public GenericBeanRegistrationCallback(String beanName, Class<?> beanClass,
        BeanDefinitionRegistry beanDefinitionRegistry) {
      super(beanName, BeanDefinitionBuilder.genericBeanDefinition(beanClass),
          beanDefinitionRegistry);
    }

  }

  /* ************************** *
   * *** DEFAULT BEAN NAMES *** * **************************
   */
  private static final String DEFAULT_JACKSON_OBJECT_MAPPER_ID = "me.sniggle.security.persona.jsonObjectMapper";
  private static final String DEFAULT_AUTHENTICATION_SUCCESS_HANDLER_ID = "me.sniggle.security.persona.authenticationSuccessHandler";
  private static final String DEFAULT_AUTHENTICATION_ENTRY_POINT_ID = "me.sniggle.security.persona.authenticationEntryPoint";
  private static final String DEFAULT_PERSONA_VERIFICATION_HANDLER_ID = "me.sniggle.security.persona.verificationHandlerId";
  private static final String DEFAULT_AUTHENTICATION_PROVIDER_ID = "me.sniggle.security.persona.authenticationProvider";
  private static final String DEFAULT_AUTHENTICATION_PROCESSING_FILTER_ID = "me.sniggle.security.persona.authenticationProcessingFilter";
  private static final String DEFAULT_LOGOUT_FILTER_ID = "me.sniggle.security.persona.logoutFilter";
  private static final String DEFAULT_AUTH_MANAGER_ID = "org.springframework.security.authenticationManager";

  /* *************************** *
   * *** THE ATTRIBUTE NAMES *** * ***************************
   */
  private static final String JACKSON_OBJECT_MAPPER_ATTR = "jackson-object-mapper-ref";
  private static final String AUTH_SUCCESS_HANDLER_ATTR = "authentication-success-handler-ref";
  // TODO private static final String AUTH_FAILURE_HANDLER_ATTR = "";
  private static final String AUTH_ENTRY_POINT_ATTR = "authentication-entry-point-ref";
  private static final String AUTH_PROVIDER_ATTR = "authentication-provider-ref";
  private static final String VERIFICATION_HANDLER_ATTR = "verification-handler-ref";
  private static final String PERSONA_AUDIENCE_ATTR = "persona-audience";
  private static final String USER_DETAILS_SERVICE_ATTR = "user-details-service-ref";
  private static final String PERSONA_SIGNUP_HANDLER_ATTR = "sign-up-handler-ref";
  private static final String AUTH_MANAGER_ATTR = "authentication-manager-ref";

  /* ********************************************** *
   * *** THE ACTUAL BEAN NAMES BEING REFERENCED *** *
   * **********************************************
   */
  private CharSequence actualJacksonObjectMapperId = DEFAULT_JACKSON_OBJECT_MAPPER_ID;
  private CharSequence actualAuthenticationSuccessHandlerId = DEFAULT_AUTHENTICATION_SUCCESS_HANDLER_ID;
  // private String actualAuthenticationEntryPointId =
  // DEFAULT_AUTHENTICATION_ENTRY_POINT_ID;
  private CharSequence actualPersonaVerificationHandlerId = DEFAULT_PERSONA_VERIFICATION_HANDLER_ID;
  // private String actualAuthenticationProviderId =
  // DEFAULT_AUTHENTICATION_PROVIDER_ID;
  private CharSequence actualAuthenticationManagerId;

  /**
   * 
   */
  public PersonaSecurityBeanDefinitionParser() {
    super();
  }

  /**
   * generic bean registration method
   * 
   * @param element
   *  the DOM element to be handled (personaAuthenticationConfiguration)
   * @param attributeName
   *  the attribute to be read
   * @param callback
   *  the {@link BeanRegistrationCallback} to be applied
   * @return the name of the bean to use or null in case of an error
   */
  private CharSequence registerBean(final Element element, final String attributeName,
      final BeanRegistrationCallback callback) {
    String beanId = element.getAttribute(attributeName);
    CharSequence result = null;
    if (!StringUtils.hasText(beanId) && callback != null) {
      callback.addConstructorArguments();
      callback.addProperties();
      callback.registerBean();
      result = callback.getBeanName();
    } else if (StringUtils.hasText(beanId)) {
      result = beanId;
    } else {

    }
    return result;
  }

  /**
   * registers the default {@link ObjectMapper} or takes the referenced one for further processing  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerJsonObjectMapper(final Element element,
      final BeanDefinitionRegistry beanDefinitionRegistry) {
    actualJacksonObjectMapperId = registerBean(element, JACKSON_OBJECT_MAPPER_ATTR,
        new RootBeanRegistrationCallback(DEFAULT_JACKSON_OBJECT_MAPPER_ID, ObjectMapper.class,
            beanDefinitionRegistry));
  }

  /**
   * registers the default Persona {@link AuthenticationSuccessHandler} or takes the referenced one for further processing  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerAuthenticationSuccessHandler(final Element element,
      final BeanDefinitionRegistry beanDefinitionRegistry) {
    actualAuthenticationSuccessHandlerId = registerBean(element, AUTH_SUCCESS_HANDLER_ATTR,
        new RootBeanRegistrationCallback(DEFAULT_AUTHENTICATION_SUCCESS_HANDLER_ID,
            PersonaAuthenticationSuccessHandler.class, beanDefinitionRegistry));
  }

  /**
   * registers the default Persona {@link AuthenticationEntryPoint} or takes the referenced one for further processing  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerAuthenticationEntryPoint(final Element element,
      final BeanDefinitionRegistry beanDefinitionRegistry) {
    registerBean(element, AUTH_ENTRY_POINT_ATTR, new RootBeanRegistrationCallback(
        DEFAULT_AUTHENTICATION_ENTRY_POINT_ID, PersonaAuthenticationEntryPoint.class,
        beanDefinitionRegistry));
  }

  /**
   * registers the default Persona {@link PersonaVerificationHandler} or takes the referenced one for further processing  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerPersonaVerificationHandler(final Element element,
      final BeanDefinitionRegistry beanDefinitionRegistry) {
    actualPersonaVerificationHandlerId = registerBean(element, VERIFICATION_HANDLER_ATTR,
        new RootBeanRegistrationCallback(DEFAULT_PERSONA_VERIFICATION_HANDLER_ID,
            PersonaVerificationHandlerImpl.class, beanDefinitionRegistry) {

          @Override
          public void addProperties() {
            addProperty("audience", element.getAttribute(PERSONA_AUDIENCE_ATTR));
            addPropertyReference("objectMapper", actualJacksonObjectMapperId.toString());
          }

        });
  }

  /**
   * registers the default Persona {@link AuthenticationProvider} or takes the referenced one for further processing  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerAuthenticationProvider(final Element element,
      BeanDefinitionRegistry beanDefinitionRegistry) {
    registerBean(element, AUTH_PROVIDER_ATTR, new RootBeanRegistrationCallback(
        DEFAULT_AUTHENTICATION_PROVIDER_ID, PersonaAuthenticationProvider.class,
        beanDefinitionRegistry) {

      @Override
      public void addProperties() {
        addPropertyReference("userDetailsService", element.getAttribute(USER_DETAILS_SERVICE_ATTR));
        addPropertyReference("personaSignUpHandler",
            element.getAttribute(PERSONA_SIGNUP_HANDLER_ATTR));
        addPropertyReference("personaVerificationHandler",
            actualPersonaVerificationHandlerId.toString());
      }

    });
  }

  /**
   * registers the {@link PersonaAuthenticationProcessingFilter}  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerAuthenticationProcessingFilter(Element element,
      BeanDefinitionRegistry beanDefinitionRegistry) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .rootBeanDefinition(PersonaAuthenticationProcessingFilter.class);
    beanDefinitionBuilder.addPropertyReference("authenticationManager",
        actualAuthenticationManagerId.toString());
    beanDefinitionBuilder.addPropertyReference("authenticationSuccessHandler",
        actualAuthenticationSuccessHandlerId.toString());
    beanDefinitionRegistry.registerBeanDefinition(DEFAULT_AUTHENTICATION_PROCESSING_FILTER_ID,
        beanDefinitionBuilder.getBeanDefinition());
  }

  /**
   * registers the {@link PersonaLogoutFilter}  
   * @param element
   *  the DOM element
   * @param beanDefinitionRegistry
   * the {@link BeanDefinitionRegistry}
   */
  private void registerLogoutFilter(Element element, BeanDefinitionRegistry beanDefinitionRegistry) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .rootBeanDefinition(PersonaLogoutFilter.class);
    beanDefinitionRegistry.registerBeanDefinition(DEFAULT_LOGOUT_FILTER_ID,
        beanDefinitionBuilder.getBeanDefinition());
  }

  /**
   * resolves the defined {@link AuthenticationManager} or assumes the default Spring Authentication Manager to be present
   * @param element
   *  the DOM element to be processed
   */
  private void resolveAuthenticationManagerReference(Element element) {
    String value = element.getAttribute(AUTH_MANAGER_ATTR);
    actualAuthenticationManagerId = StringUtils.hasText(value) ? value : DEFAULT_AUTH_MANAGER_ID;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.beans.factory.xml.AbstractBeanDefinitionParser#
   * parseInternal(org.w3c.dom.Element,
   * org.springframework.beans.factory.xml.ParserContext)
   */
  @Override
  protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
    if ("personaAuthenticationConfiguration".equals(element.getLocalName())) {
      registerPersonaAuthentication(element, parserContext);
    }
    return null;
  }

  private void registerPersonaAuthentication(Element element, ParserContext parserContext) {
    BeanDefinitionRegistry beanDefinitionRegistry = parserContext.getRegistry();
    registerJsonObjectMapper(element, beanDefinitionRegistry);
    registerPersonaVerificationHandler(element, beanDefinitionRegistry);
    registerAuthenticationSuccessHandler(element, beanDefinitionRegistry);
    registerAuthenticationEntryPoint(element, beanDefinitionRegistry);
    registerAuthenticationProvider(element, beanDefinitionRegistry);
    resolveAuthenticationManagerReference(element);
    registerAuthenticationProcessingFilter(element, beanDefinitionRegistry);
    registerLogoutFilter(element, beanDefinitionRegistry);
  }

}

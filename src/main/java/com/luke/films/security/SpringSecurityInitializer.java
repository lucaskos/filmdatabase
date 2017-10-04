package com.luke.films.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * WebAplicationInitializer load Spring Context Loader automatically.
 * Since we are using WebAplicationInitializer the web.xml is not required
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}

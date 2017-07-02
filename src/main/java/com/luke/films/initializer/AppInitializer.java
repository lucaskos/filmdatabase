package com.luke.films.initializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.luke.films.config.ApplicationConfigCore;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/*
	 * Auto detect default Servlet for the container at startup time (tomcat...)
	 */
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		WebApplicationContext context = (WebApplicationContext)super.createRootApplicationContext();
	    ((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles("test");
	    return context;
	}
	
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationConfigCore.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
}

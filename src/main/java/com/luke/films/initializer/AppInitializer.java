package com.luke.films.initializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.luke.films.config.ApplicationConfigCore;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		 WebApplicationContext context = getContext();
//		 servletContext.addListener(new ContextLoaderListener(context));
//		 ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DipatcherServlet", new DispatcherServlet(context));
//		 dispatcher.setLoadOnStartup(1);
//		 dispatcher.addMapping("/");
//	}
//	
//	private AnnotationConfigWebApplicationContext getContext() {
//		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//		context.setConfigLocation("com.luke.films.config");
//		return context;
//	}
	
	/*
	 * Auto detect default Servlet for the container at startup time (tomcat...)
	 */
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
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

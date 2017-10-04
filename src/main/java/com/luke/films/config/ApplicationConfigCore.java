package com.luke.films.config;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.luke.films.security.SecurityConfig;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan(basePackages = { "com.luke.*"})
@Import(value = { SecurityConfig.class, HibernateConfig.class })
public class ApplicationConfigCore extends WebMvcConfigurerAdapter {

	@Profile("dev")
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/films");
		dataSource.setUsername("root");
		dataSource.setPassword("lucas7");
		/*
		dataSource.setDriverClassName("${dataSource.driver_class_name}");
		dataSource.setUrl("${dataSource.url}");
		dataSource.setUsername("${dataSource.username}");
		dataSource.setPassword("${dataSource.password}");
		*/
		return dataSource;
	}
	@Profile("test")
	@Bean
	public DataSource getTestDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11183110");
		dataSource.setUsername("sql11183110");
		dataSource.setPassword("NaCzf7t6T6");
		return dataSource;
	}
	/*
	 * Configure TilesConfigurer to deal with Apache Tiles
	 */
	@Bean
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		//configurer.setDefinitions(new String[] { "${tiles.configurer.definitions}" });
		configurer.setDefinitions(new String[] { "/WEB-INF/defs/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	/*
	 * Configure ViewResolvers to deliver preferred views
	 */
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	/**
	 * Serving static resources through configuration of ResourceHandler
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("${registry.static.resources.path}").addResourceLocations("${registry.static.resources.location}");
	}

	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("${messages.source.basename}");
		return messageSource;
	}

}

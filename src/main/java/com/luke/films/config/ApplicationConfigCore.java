package com.luke.films.config;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
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
@PropertySource(
		value={"classpath:spring.properties"},
		ignoreResourceNotFound = true)
public class ApplicationConfigCore extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(ApplicationConfigCore.class);

	@Autowired
	Environment env;

	@Profile("dev")
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(ConfigurationConstants.DATASOURCE_PATH));
		dataSource.setUrl(env.getProperty(ConfigurationConstants.DATASOURCE_URL));
		dataSource.setUsername(env.getProperty(ConfigurationConstants.DATASOURCE_USERNAME));
		dataSource.setPassword(env.getProperty(ConfigurationConstants.DATASOURCE_PASSWORD));
		LOGGER.info("DataSource settings " + env.toString());
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
	/**
	 * Configure TilesConfigurer to deal with Apache Tiles
	 */
	@Bean
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { env.getProperty(ConfigurationConstants.TILES_CONFIGURATION_DEFINITION) });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	/**
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
		registry.addResourceHandler(env.getProperty(ConfigurationConstants.STATIC_RESOURCES_NAME)).addResourceLocations(env.getProperty(ConfigurationConstants.STATIC_RESOURCES_PATH));
	}

	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		return messageSource;
	}

	@Bean
	private static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propSourceConfigurer = new PropertySourcesPlaceholderConfigurer();
		//propSourceConfigurer.setLocation(new ClassPathResource("spring.properties"));
		//propSourceConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return new PropertySourcesPlaceholderConfigurer();
	}

}

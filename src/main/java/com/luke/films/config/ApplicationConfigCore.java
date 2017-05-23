package com.luke.films.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.luke.films.security.SecurityConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.luke.films.*" })
@Import(value = { SecurityConfig.class })
public class ApplicationConfigCore extends WebMvcConfigurerAdapter {

	/*
	 * Configure TilesConfigurer to deal with Apache Tiles
	 */
	@Bean
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "WEB-INF/defs/tiles.xml" });
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

	/*
	 * Serving static resources through configuration of ResourceHandler
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
	}

}

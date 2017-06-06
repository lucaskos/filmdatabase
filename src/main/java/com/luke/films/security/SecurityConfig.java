package com.luke.films.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity //helps configure security from WebSecurityConfigurer class
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setSessionAttributeName("_csrf");
		return repository;
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//Quering DB
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}


	/*
	 * method from WebSecurityConfigurerAdapter class
	 * configure() method is used to configure HttpSecurity class
	 * security login, logout and exception handling configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    .authorizeRequests()
			.antMatchers("/addfilm").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
			.and()
		.formLogin().loginPage("/login").failureUrl("/login?error")
			    .usernameParameter("username").passwordParameter("password")
			.and()
			    .logout().logoutSuccessUrl("/login?logout")
			.and()
				.exceptionHandling().accessDeniedPage("/denied")
			.and()
			    .csrf();
	    
	    http
	    .authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
		.formLogin().loginPage("/login").failureUrl("/login?error")
			    .usernameParameter("username").passwordParameter("password")
			.and()
			    .logout().logoutSuccessUrl("/login?logout")
			.and()
				.exceptionHandling().accessDeniedPage("/denied")
			.and()
			    .csrf();
	    
	    http.authorizeRequests().antMatchers("/static/**").permitAll();
	}
	
}

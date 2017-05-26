package com.luke.films.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
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
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//Quering DB
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery("select id, username,password, enabled from users where username=?")
//		.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
		
		auth.inMemoryAuthentication().withUser("lucaskos").password("lucaskos").roles("ADMIN");
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
//	    http
//	    .authorizeRequests()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//			.and()
//		.formLogin().loginPage("/login").failureUrl("/login?error")
//			    .usernameParameter("username").passwordParameter("password")
//			.and()
//			    .logout().logoutSuccessUrl("/login?logout")
//			.and()
//				.exceptionHandling().accessDeniedPage("/denied")
//			.and()
//			    .csrf();
	    http
	    .authorizeRequests()
			.antMatchers("/addfilm").access("hasRole('ROLE_ADMIN')")
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

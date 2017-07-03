package com.luke.films.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luke.films.model.user.UserDao;
import com.luke.films.model.user.role.Role;

@Service

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.luke.films.model.user.User activeUsr = userDao.getUser(username);
		List<GrantedAuthority> authorities = buildUserAuthority(activeUsr.getRole());
		
		return buildUserForAuthentication(activeUsr, authorities);
	}
	/**
	 * Converts the user from com.luke.films.dao package
	 * to org.springframework.security.core.userdetails.User
	 */
	private User buildUserForAuthentication(com.luke.films.model.user.User user,
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
			user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Role userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}

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

import com.luke.user.model.Role;
import com.luke.user.model.UserDao;
import com.luke.user.model.UserDaoImpl;

@Service

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.luke.user.model.User activeUsr = userDao.getUser(username);
		List<GrantedAuthority> authorities = buildUserAuthority(activeUsr.getUsersRoles());
		
		return buildUserForAuthentication(activeUsr, authorities);
	}
	/*
	 * Converts the user from com.luke.films.dao package
	 * to org.springframework.security.core.userdetails.User
	 */
	private User buildUserForAuthentication(com.luke.user.model.User user,
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
			user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}

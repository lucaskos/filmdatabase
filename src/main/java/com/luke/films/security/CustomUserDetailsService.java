package com.luke.films.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luke.films.dao.User;
import com.luke.films.dao.UserDao;
import com.luke.films.dao.UserRole;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User activeUsr = userDao.getUser(username);
		Collection<UserRole> usersRoles = activeUsr.getUsersRoles();
		UserDetails userDetails = (UserDetails) new User(activeUsr.getUsername(), activeUsr.getPassword(), usersRoles);
		return userDetails;
	}

}

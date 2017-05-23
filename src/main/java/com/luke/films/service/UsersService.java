package com.luke.films.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luke.films.dao.User;
import com.luke.films.dao.UserDao;

@Service
public class UsersService {

	@Autowired
	private UserDao usersDao;
	
	public boolean createUser(User user){
		return usersDao.createUser(user);
	}
	
}

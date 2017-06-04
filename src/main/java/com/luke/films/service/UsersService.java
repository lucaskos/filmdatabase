package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.dao.User;
import com.luke.films.dao.UserDao;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UserDao usersDao;
	
	public void createUser(User user){
		usersDao.createUser(user);
	}
	
	public boolean checkUsername(User user) {
		return usersDao.checkUserExist(user);
	}
	
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	public void removeUser(User user) {
		usersDao.removeUser(user);
	}
	
}

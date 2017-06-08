package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luke.user.model.User;
import com.luke.user.model.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao usersDao;
	
	public void createUser(User user){
		usersDao.createUser(user);
	}
	
	public boolean checkUsername(User user) {
		return usersDao.checkUserExist(user);
	}
	//TODO Securing this method for ADMIN only
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	//TODO securing this method
	/*
	 * only user who account belongs can delete his account
	 * should redirect to new page when user deleting his account
	 */
	public void removeUser(User user) {
		usersDao.removeUser(user);
	}
	
}

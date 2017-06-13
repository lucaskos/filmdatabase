package com.luke.films.model.user;

import java.util.List;

public interface UserDao {

	void createUser(User user);

	List<User> getAllUsers();

	User getUser(String username);

	void removeUser(User user);
	
	void update(User user);

}
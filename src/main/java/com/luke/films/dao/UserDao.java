package com.luke.films.dao;

import java.util.List;

public interface UserDao {

	void createUser(User user);

	boolean checkUserExist(User user);

	List<User> getAllUsers();

	User getUser(String username);

	void removeUser(User user);

}

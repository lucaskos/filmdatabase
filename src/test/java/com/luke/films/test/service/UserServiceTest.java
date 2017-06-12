package com.luke.films.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.model.user.User;
import com.luke.films.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class UserServiceTest {

	@Autowired
	private UserService usersService;

	@Test
	public void userTest() {
		
		User user1 = new User("user1", "password1", "email1@email.com");
		User user2 = new User("user2", "password2", "email2@email.com");
		
		//user that will not be in db
		User userNotInDb = new User("user3", "password3", "email3@email.com");

		if (usersService.getUser(user1.getUsername()) != null || usersService.getUser(user2.getUsername()) != null) {
			usersService.removeUser(user1);
			usersService.removeUser(user2);
		}

		// check if two users are not the same
		assertNotEquals(user1, user2);

		// creating users in db
		usersService.createUser(user1);
		usersService.createUser(user2);
		
		//checking if users created above equal the one from db
		assertEquals(user1, usersService.getUser(user1.getUsername()));
		assertEquals(user2, usersService.getUser(user2.getUsername()));
		
		//checking if username exists
		assertTrue(usersService.checkUsername(user1));
		assertTrue(usersService.checkUsername(user2));
		assertFalse(usersService.checkUsername(userNotInDb)); //should return false
		
		List<User> allUsers = usersService.getAllUsers();
		//checking if users in db have been populated
		assertNotNull(allUsers);
		
		//removing users
		usersService.removeUser(user1);
		usersService.removeUser(user2);
		
		//checking if user that has been removed really not exists in db
		assertNull(usersService.getUser(user1.getUsername()));
		assertNull(usersService.getUser(user2.getUsername()));
		assertNull(usersService.getAllUsers());
	}

}

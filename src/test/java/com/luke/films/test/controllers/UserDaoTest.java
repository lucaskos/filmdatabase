package com.luke.films.test.controllers;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.dao.User;
import com.luke.films.dao.UserDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfigCore.class, HibernateConfig.class})
@WebAppConfiguration("WebContent")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	//@Autowired
	//private DataSource dataSource;

	@Test
	public void getUser() {
		List<User> userList = userDao.getAllUsers();
		for(User u : userList) 
			System.out.println("\n"+u+"\n");
		 Assert.assertEquals("Hello world!", "Hello world!");
		 System.out.println("\n\nhibernate template : " + userDao.getUser("lucaskos"));
		 
	}
}

package com.luke.films.test.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.luke.films.controllers.HomeController;

public class HomeControllerTest {

	@Test
	public void testHomePage(){
		HomeController homeController = new HomeController();
		assertEquals("home", homeController.showHomePage());
		assertEquals("error", homeController.error());
	}
}

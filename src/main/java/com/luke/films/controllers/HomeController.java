package com.luke.films.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	private static final String ACCESS_DENIED_STATUS_CODE = "404 - Access Denied";

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String showHomePage() {
		return "home";
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied(Principal user) {
		logger.info(ACCESS_DENIED_STATUS_CODE + " for user " + user.getName());
		final String accessDeniedMessage = "This page is unavailable for you.";
		ModelAndView model = new ModelAndView();
		if (user != null) {
			List<String> errorsList = new ArrayList<String>();
			errorsList.add(ACCESS_DENIED_STATUS_CODE);
			errorsList.add(accessDeniedMessage);
			model.addObject("user", user.getName());
			model.addObject("error", errorsList);
		}
		return model;
	}


}

package com.luke.films.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.luke.films.common.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final String HOME_PAGE = "home";

	@RequestMapping(value = {"/", "/" + HOME_PAGE}, method = RequestMethod.GET)
	public String showHomePage() {
		return HOME_PAGE;
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied(Principal user) {
		final String statusCode = "404 - Access denied!";
		final String accessDeniedMessage = "This page is unavailable for you.";
		ModelAndView model = new ModelAndView();
		if (user != null) {
			List<String> errorsList = new ArrayList<String>();
			errorsList.add(statusCode);
			errorsList.add(accessDeniedMessage);
			model.addObject(ControllerConstants.USER, user.getName());
			model.addObject(ControllerConstants.ERROR, errorsList);
		}
		return model;
	}
}

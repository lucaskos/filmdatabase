package com.luke.films.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String showHomePage(){
		return "home";
	}
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied(Principal user) {
		ModelAndView model = new ModelAndView();
		if(user != null) {
			model.addObject("user", user.getName());
		}
		
		return model;
	}
	@RequestMapping(value = "/error")
	public String error(){
		return "error";
	}
}

package com.luke.films.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

@Controller
public class HomeController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showHomePage() {
		return "home";
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("user", user.getName());
		}

		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception ex) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errMsg", "This is Exception.class");
		return mav;
	}

	@RequestMapping(value = "/addactor", method = RequestMethod.GET)
	public String addActor(Model model) {
		model.addAttribute("actor", new Actor());
		return "addactor";
	}

	@RequestMapping(value = "/addfilm")
	public String addFilm(Model model) {
		model.addAttribute("film", new Film());
		return "addfilm";
	}
}

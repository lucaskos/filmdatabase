package com.luke.films.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.service.FilmsService;

@Controller
public class FilmsController {
	
	@Autowired
	private FilmsService filmsService;
	
	@RequestMapping(value="/filmslist", method=RequestMethod.GET)
	public String showBookList(Model model) {
		model.addAttribute("film" ,filmsService.getFilms());
		return "filmslist";
	}
	
	@RequestMapping(value="/addfilm")
	@Secured("hasRole('USER')")
	public String addFilm() {
		return "addfilm";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String filmAdded(){
		return "filmcreated";
	}

}

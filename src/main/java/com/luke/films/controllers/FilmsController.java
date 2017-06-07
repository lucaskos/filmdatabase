package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.model.film.Film;
import com.luke.films.service.FilmService;

@Controller
public class FilmsController {
	
	@Autowired
	private FilmService filmsService;
	
	
	@RequestMapping(value="/filmslist", method=RequestMethod.GET)
	public String showBookList(Model model) {
		model.addAttribute("film" ,filmsService.getAllFilms());
		return "filmslist";
	}
	
	@RequestMapping(value="/addfilm")
	public String addFilm(Model model) {
		
	model.addAttribute("film", new Film());
		return "addfilm";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String filmAdded(@Valid Film film, BindingResult results){
		if(results.hasErrors()) {
			return "addfilm";
		}
		filmsService.addFilm(film);
		return "filmcreated";
	}
	


}

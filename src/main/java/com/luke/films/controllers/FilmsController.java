package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.dao.Film;
import com.luke.films.service.FilmsService;

@Controller
public class FilmsController {
	
	@Autowired
	private FilmsService filmsService;
	
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
	
	@RequestMapping(value="/{filmId}", method=RequestMethod.GET)
	public String getFilm(@PathVariable int filmId, Model model){
		model.addAttribute(filmsService.getFilmById(filmId));
		return "film";
	}

}

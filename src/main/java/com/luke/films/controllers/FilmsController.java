package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.model.ActorFilm;
import com.luke.films.model.ActorFilmDao;
import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;
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
		Actor actor = new Actor();
		model.addAttribute(actor);
		
		
		
		Film filmById = filmsService.getFilmById(filmId);
		model.addAttribute("film", filmById);
		return "film";
	}

}

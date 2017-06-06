package com.luke.films.controllers;

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
public class ActorFilmController {
	@Autowired
	private ActorFilmDao actorFilmDao;
	@Autowired
	private FilmsService filmsService;
	
	private int filmId;
	
	@RequestMapping(value = "/actoraddedtofilm", method=RequestMethod.POST)
	public String addActorToFilm(Actor actor, BindingResult results){
		System.out.println(filmId);
		actorFilmDao.addActorToFilm(filmsService.getFilmById(filmId), actor, "NEO");
		return "filmlist";
	}

	@RequestMapping(value="/{filmId}", method=RequestMethod.GET)
	public String getFilm(@PathVariable int filmId, Model model, Actor actor, ActorFilm actorFilm){

		this.filmId = filmId;
		if(actor == null) actor = new Actor();
		
		model.addAttribute("actor", actor);
		Film filmById = filmsService.getFilmById(filmId);
		model.addAttribute("film", filmById);
		return "film";
	}
}

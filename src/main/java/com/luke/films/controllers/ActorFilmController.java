package com.luke.films.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.model.ActorFilm;
import com.luke.films.model.ActorFilmDao;
import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;
import com.luke.films.service.ActorFilmService;
import com.luke.films.service.FilmService;

@Controller
public class ActorFilmController {
	@Autowired
	private ActorFilmService actorFilmService;
	@Autowired
	private FilmService filmsService;

	private int filmId;

	@RequestMapping(value = "/actoraddedtofilm", method = RequestMethod.POST)
	public String addActorToFilm(ActorFilm actorFilm) {
		Film filmById = filmsService.getFilmById(filmId);
		actorFilm.setFilm(filmById);
		System.out.println(actorFilm.getRole() + " : " + actorFilm.getActor() + " : " + actorFilm.getFilm());
		actorFilmService.addActorToFilm(filmById, actorFilm.getActor(), actorFilm.getRole());
		return "redirect:/filmslist";
	}

	@RequestMapping(value = "/{filmId}", method = RequestMethod.GET)
	public String getFilm(@PathVariable int filmId, Model model, Actor actor, ActorFilm actorFilm) {
		
		this.filmId = filmId;
		Film filmById = filmsService.getFilmById(filmId);

		List<ActorFilm> list = actorFilmService.getActors(filmById);
			
		if (filmById == null)
			return "error";
		else {
			if(list!=null) {
					model.addAttribute("actorfilm", list);
			}
			model.addAttribute("film", filmById);
			model.addAttribute("actorFilm", new ActorFilm());
			return "film";
		}
	}
}

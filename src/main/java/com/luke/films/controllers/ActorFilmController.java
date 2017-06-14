package com.luke.films.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.cast.Cast;
import com.luke.films.model.film.Film;
import com.luke.films.service.CastService;
import com.luke.films.service.FilmService;

@Controller
public class ActorFilmController {
	@Autowired
	private CastService actorFilmService;
	@Autowired
	private FilmService filmsService;

	private int filmId;

	@RequestMapping(value = "/actoraddedtofilm", method = RequestMethod.POST)
	public String addActorToFilm(Cast actorFilm) {
		Film filmById = filmsService.getFilmById(filmId);
		actorFilm.setFilm(filmById);
		actorFilmService.addActorToFilm(filmById, actorFilm.getActor(), actorFilm.getRole());
		return "redirect:/filmslist";
	}

	@RequestMapping(value = "/film/{filmId}", method = RequestMethod.GET)
	public String getFilmPage(@PathVariable int filmId, Model model, Actor actor, Cast actorFilm) {
		this.filmId = filmId;
		Film filmById = filmsService.getFilmById(filmId);

		List<Cast> list = actorFilmService.getActors(filmById);
			
		if (filmById == null)
			return "error";
		else {
			if(list!=null) {
					model.addAttribute("actorfilm", list);
			}
			model.addAttribute("filmRating", filmsService.getRating(filmById));
			model.addAttribute("film", filmById);
			model.addAttribute("actorFilm", new Cast());
			return "film";
		}
	}
	
	@RequestMapping(value = "/actor/{actorId}", method = RequestMethod.GET)
	public String getActorPage(@PathVariable("actorId") int actorId) {
		System.out.println(actorId);
		return "actor";
	}
}

package com.luke.films.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.cast.Cast;
import com.luke.films.model.film.Film;
import com.luke.films.service.ActorService;
import com.luke.films.service.CastService;
import com.luke.films.service.FilmService;

@Controller
public class ActorFilmController {
	@Autowired
	private CastService castService;
	@Autowired
	private FilmService filmsService;
	
	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "/actoraddedtofilm", method = RequestMethod.GET)
	public String addActorToFilm(@RequestParam("actorId") String actorId, @RequestParam("filmId") String filmId, @RequestParam() String role) {
		
		Actor actor = actorService.getActorById(Integer.valueOf(actorId));
		Film film = filmsService.getFilmById(Integer.valueOf(filmId));
		
		castService.addActorToFilm(film, actor, role);
		return "redirect:/filmslist";
	}

	@RequestMapping(value = "/film/{filmId}", method = RequestMethod.GET)
	public String getFilmPage(@PathVariable int filmId, Model model, Actor actor, Cast actorFilm) {
		Film filmById = filmsService.getFilmById(filmId);

		List<Cast> list = castService.getActors(filmById);
			
		if (filmById == null)
			return "error";
		else {
			if(list!=null) {
					model.addAttribute("actorfilm", list);
			}
			model.addAttribute("filmRating", filmsService.getRating(filmById));
			model.addAttribute("film", filmById);
			model.addAttribute("noOfVotes", filmById.getRating().size());
			model.addAttribute("actorFilm", new Cast());
			return "film";
		}
	}
	
	@RequestMapping(value = "/actor/{actorId}", method = RequestMethod.GET)
	public String getActorPage(@PathVariable("actorId") int actorId, Model model, Cast actorFilm) {
		Actor actor = actorService.getActorById(actorId);
		Map<Film, String> map = castService.getFilmography(actor);
		model.addAttribute("films", map);
		model.addAttribute("actor", actor);
		return "actor";
	}
}

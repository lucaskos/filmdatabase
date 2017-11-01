package com.luke.films.controllers;

import java.util.List;
import java.util.Map;

import com.luke.films.common.ControllerConstants;
import com.luke.films.model.comment.Comment;
import com.luke.films.model.comment.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luke.films.model.actor.Person;
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
	@Autowired
	private CommentDao commentDao;

	private List<Comment> comments;

	@RequestMapping(value = "/actoraddedtofilm", method = RequestMethod.GET)
	public String addActorToFilm(@RequestParam("actorId") String actorId, @RequestParam("filmId") String filmId,
			@RequestParam() String role) {

		Person person = actorService.getActorById(Integer.valueOf(actorId));
		Film film = filmsService.getFilmById(Integer.valueOf(filmId));

		castService.addActorToFilm(film, person, role);
		return ControllerConstants.REDIRECT+ ControllerConstants.FILM_LIST;
	}

	@RequestMapping(value = "/film/{filmId}", method = RequestMethod.GET)
	public String getFilmPage(@PathVariable int filmId, Model model, Person person, Cast actorFilm) {
		Film filmById = filmsService.getFilmById(filmId);
		List<Cast> list = castService.getActors(filmById);
		comments = commentDao.getFilmComments(filmId);

		if (filmById == null) {
			final String filmError = "No film of id " + filmId + " found!";
			throw new NullPointerException(filmError);
		} else {
			if (list != null) {
				model.addAttribute("actorfilm", list);
			}
			model.addAttribute("filmRating", filmsService.getRating(filmById));
			model.addAttribute(ControllerConstants.FILM, filmById);
			model.addAttribute("noOfVotes", 0); //filmById.getRating().size());
			model.addAttribute("actorFilm", new Cast());
			model.addAttribute(ControllerConstants.COMMENTS_LIST, comments);
			return ControllerConstants.FILM;
		}
	}

	@RequestMapping(value = "/actor/{actorId}", method = RequestMethod.GET)
	public String getActorPage(@PathVariable("actorId") int actorId, Model model, Cast actorFilm) {
		Person person = actorService.getActorById(actorId);

		comments = commentDao.getActorComments(actorId);

		if (person == null) {
			final String actorError = "No person of id " + actorId + " found!";
			throw new NullPointerException(actorError);
		} else {

			if(!comments.isEmpty()) {
				model.addAttribute(ControllerConstants.COMMENTS_LIST, comments);
			}
			Map<Film, String> map = castService.getFilmography(person);
			model.addAttribute(ControllerConstants.FILMS, map);
			model.addAttribute(ControllerConstants.ACTOR, person);
			return ControllerConstants.ACTOR;
		}
	}
}

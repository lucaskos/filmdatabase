package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luke.films.model.Actor;
import com.luke.films.model.ActorDao;
import com.luke.films.model.ActorFilmDao;
import com.luke.films.model.Film;
import com.luke.films.service.FilmsService;

@Controller
public class ActorController {

	@Autowired
	private ActorDao actorDao;

	@Autowired
	private FilmsService filmsService;

	@RequestMapping(value = "/addactor", method = RequestMethod.POST)
	public String addactor(@ModelAttribute("actor") @Valid Actor actor, Film film, BindingResult results) {
		int filmId = film.getFilmId();
		System.out.println(filmId);
				actorDao.addActor(actor);
		return "filmslist";

	}

}

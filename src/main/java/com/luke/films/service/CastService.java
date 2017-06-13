package com.luke.films.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.cast.Cast;
import com.luke.films.model.cast.CastDao;
import com.luke.films.model.film.Film;

@Transactional
@Component
public class CastService {

	@Autowired
	private CastDao castDao;

	public void addActorToFilm(Film film, Actor actor, String role) {
		castDao.addActorToFilm(film, actor, role);
	}

	public List<Cast> getActors(Film film) {
		return castDao.getCast(film);
	}

	public Map<Film, String> getFilmography(Actor actor) {
		return castDao.getFilmography(actor);
	}
}
package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.ActorFilm;
import com.luke.films.model.ActorFilmDao;
import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

@Transactional
@Component
public class ActorFilmService {

	@Autowired
	private ActorFilmDao actorFilmDao;

	public void addActorToFilm(Film film, Actor actor, String role) {
		actorFilmDao.addActorToFilm(film, actor, role);
	}

	public List<ActorFilm> getActors(Film film) {
		return actorFilmDao.getActors(film);
	}
}

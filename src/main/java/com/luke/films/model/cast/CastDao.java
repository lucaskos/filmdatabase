package com.luke.films.model.cast;

import java.util.List;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

public interface CastDao {

	void addActorToFilm(Film film, Actor actor, String role);
	
	List<Cast> getCastOfFilm(Film film);
	
	List<Cast> getFilmographyOfActor(Actor actor);
	
}

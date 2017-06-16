package com.luke.films.model.film;

import java.util.List;

import com.luke.films.model.rating.Rating;

public interface FilmsDao {

	List<Film> getAllFilms();

	void addFilm(Film film);
	
	Film getFilmById(int id);
	
	List<Film> getFilmsByYear(int year);

	void deleteFilm(Film film);

	List<Film> getFilmsByTitle(String title);

	void addRating(Rating rating);

}
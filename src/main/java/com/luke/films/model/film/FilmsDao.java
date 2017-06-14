package com.luke.films.model.film;

import java.util.List;

public interface FilmsDao {

	List<Film> getAllFilms();

	void addFilm(Film film);
	
	Film getFilmById(int id);
	
	Film getFilmByTitle(String title);
	
	List<Film> getFilmsByYear(int year);

	void deleteFilm(Film film);

	List<Film> findFilms(int id, int count);

	
}
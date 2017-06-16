package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.model.rating.RatingDao;


@Component("filmsService")
@Transactional
public class FilmService {
	@Autowired
	private FilmsDao filmsDao;
	
	@Autowired
	private RatingDao ratingDao;
	
	public List<Film> getAllFilms(){
		return filmsDao.getAllFilms();
	}
	
	public void addFilm(Film film){
		filmsDao.addFilm(film);
	}
	
	public void deleteFilm(Film film){
		filmsDao.deleteFilm(film);
	}
	
	public void deleteById(int id) {
		Film filmById = getFilmById(id);
		filmsDao.deleteFilm(filmById);
	}
	
	public Film getFilmByTitle(String title) {
		if(title.equals("") || title == null)
			return null;
		else {
			List<Film> list = filmsDao.getFilmsByTitle(title);
			if(list.isEmpty())
				return null;
			return list.get(0);
		}
	}
	
	public Film getFilmById(int id) {
		return filmsDao.getFilmById(id);
	}
	
	public List<Film> getFilmsByYear(int year) {
		return filmsDao.getFilmsByYear(year);
	}
	//TODO remove or implement this
	public List<Film> findFilms(int max, int count){
		return filmsDao.findFilms(max, count);
	}
	
	public float getRating(Film film) {
		return ratingDao.getRating(film);
	}
}

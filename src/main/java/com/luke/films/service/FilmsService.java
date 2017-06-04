package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luke.films.dao.Film;
import com.luke.films.dao.FilmsDao;


@Component("filmsService")
public class FilmsService {
	@Autowired
	private FilmsDao filmsDao;
	
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
		filmsDao.deleteById(id);
	}
	
	public Film getFilmByTitle(String title) {
		return filmsDao.getFilmByTitle(title);
	}
	
	public Film getFilmById(int id) {
		return filmsDao.getFilmById(id);
	}
	
	public List<Film> getFilmsByYear(int year) {
		return filmsDao.getFilmsByYear(year);
	}
}

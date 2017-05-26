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
	
	public List<Film> getFilms(){
		return filmsDao.getFilms();
	}
	
	public void addFilm(Film film){
		filmsDao.addFilm(film);
	}
}

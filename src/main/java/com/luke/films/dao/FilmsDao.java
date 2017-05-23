package com.luke.films.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("filmsDao")
public class FilmsDao {

	public List<Film> getFilms() {
		List<Film> list = new ArrayList<Film>(){
			{
			add(new Film());
			}
		};
		return list;
	}
	
	public boolean addFilm(Film film) {
		
		
		
		return false;
	}
	
	
}

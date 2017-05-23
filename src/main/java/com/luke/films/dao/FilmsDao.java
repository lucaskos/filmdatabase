package com.luke.films.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("booksDao")
public class FilmsDao {

	public List<Film> getFilms() {
		List<Film> list = new ArrayList<Film>(){
			{
			add(new Film("Harry Poter", "333", new Author("rowing")));
			add(new Film("Short history of Time", "333", new Author("Hawking")));
			add(new Film("Shining", "666", new Author("King")));
			}
		};
		return list;
	}
	
	
}

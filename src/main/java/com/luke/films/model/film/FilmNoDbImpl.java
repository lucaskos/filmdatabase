package com.luke.films.model.film;

import java.util.ArrayList;
import java.util.List;

import com.luke.films.model.rating.Rating;

public class FilmNoDbImpl implements FilmsDao {
	private List<Film> films;
	public FilmNoDbImpl() {

		Film film1 = new Film("Matrix", 1999, "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.");
		Film film2 = new Film("Lord of the rings", 2002, "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.");
		films = new ArrayList<Film>();
		films.add(film1);
		films.add(film2);
		
	}

	@Override
	public List<Film> getAllFilms() {
		return films;
	}

	@Override
	public void addFilm(Film film) {
		films.add(film);
	}

	@Override
	public Film getFilmById(int id) {
		return films.get(id);
	}

	@Override
	public List<Film> getFilmsByYear(int year) {
		List<Film> filmsByYear = new ArrayList<Film>();
		for(Film f : films) {
			if(f.getYear() == year)
				filmsByYear.add(f);
		}
		return filmsByYear;
	}

	@Override
	public void deleteFilm(Film film) {
		films.remove(film);
	}

	@Override
	public List<Film> getFilmsByTitle(String title) {
		List<Film> filmsByTitle = new ArrayList<Film>();
		for(Film f : films)
			if(f.getTitle().equalsIgnoreCase(title))
				filmsByTitle.add(f);
		
		return filmsByTitle;
	}

	@Override
	public void addRating(Rating rating) {
		// TODO Auto-generated method stub

	}

}

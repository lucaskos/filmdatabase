package com.luke.films.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.model.rating.Rating;
import com.luke.films.model.rating.RatingDao;
import com.luke.films.model.user.User;

@Component("filmsService")
@Transactional
public class FilmService {
	@Autowired
	private FilmsDao filmsDao;

	public List<Film> getAllFilms() {
		return filmsDao.getAllFilms();
	}

	public void addFilm(Film film) {
		filmsDao.addFilm(film);
	}

	public void deleteFilm(Film film) {
		filmsDao.deleteFilm(film);
	}

	public void deleteById(int id) {
		Film filmById = getFilmById(id);
		filmsDao.deleteFilm(filmById);
	}

	public Film getFilmByTitle(String title) {
		if (title.equals("") || title == null)
			return null;
		else {
			List<Film> list = filmsDao.getFilmsByTitle(title);
			if (list.isEmpty())
				return null;
			return list.get(0);
		}
	}
	public Film getFilmById(int primaryKey) {
		return filmsDao.getFilmById(primaryKey);
	}

	public List<Film> getFilmsByYear(int year) {
		return filmsDao.getFilmsByYear(year);
	}

	public float getRating(Film film) {
		Set<Rating> ratingForFilm = getFilmById(film.getFilmId()).getRating();
		if(ratingForFilm.size() < 1)
			return 0.0f;
		float calculateRating = calculateRating(ratingForFilm);
		return calculateRating;
	}

	private float calculateRating(Set<Rating> ratingForFilm) {
		float actualRating = 0.0f;
		for(Rating r : ratingForFilm)
			actualRating += r.getRating();
		return actualRating/ratingForFilm.size();
	}
	
	public void rateFilm(Film film, User user, int rating) {
		
		
		Set<Rating> userRating = film.getRating();
		
		if(userRating.isEmpty()) {
			filmsDao.addRating(new Rating(rating, user, film));
		} else {
			Rating didUserRateFilm = didUserRateFilm(userRating, user);
			didUserRateFilm.setRating(rating);
			didUserRateFilm.setFilm(film);
			didUserRateFilm.setRating(rating);
			System.out.println(didUserRateFilm);
			filmsDao.addRating(didUserRateFilm);
		}
	}
	
	private Rating didUserRateFilm(Set<Rating> filmRatings, User user) {
		for(Rating rating : filmRatings) 
			if(rating.getUser().equals(user))
				return rating;
		return new Rating();
	}
	
}

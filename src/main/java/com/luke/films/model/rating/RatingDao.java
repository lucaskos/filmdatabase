package com.luke.films.model.rating;

import com.luke.films.model.film.Film;
import com.luke.films.model.user.User;

public interface RatingDao {
	void rateFilm(Film film, User user, int rating);

	Rating getCurrentRating(Film film, User user);

	boolean isRated(Film film, User user);
	
	float getRating(Film film);
}

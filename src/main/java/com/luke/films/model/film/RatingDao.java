package com.luke.films.model.film;

import com.luke.user.model.User;

public interface RatingDao {
	void rateFilm(Film film, User user, int rating);

	Rating getCurrentRating(Film film, User user);

	boolean isRated(Film film, User user);
}

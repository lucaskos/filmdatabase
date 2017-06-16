package com.luke.films.model.rating;

import java.util.List;

import com.luke.films.model.film.Film;
import com.luke.films.model.user.User;

public interface RatingDao {
	void rateFilm(Film film, User user, int rating);

	boolean isRated(Film film, User user);
}

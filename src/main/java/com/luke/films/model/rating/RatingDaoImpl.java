package com.luke.films.model.rating;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.model.user.User;

@Component
@Transactional
public class RatingDaoImpl implements RatingDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	FilmsDao filmsDao;

	@Override
	public void rateFilm(Film film, User user, int rating) {
		Rating filmRating = null;
		if (isRated(film, user)) {
			filmRating.setRating(rating);
			sessionFactory.getCurrentSession().update(filmRating);
		} else {
			filmRating = new Rating(rating, user, film);
			sessionFactory.getCurrentSession().save(filmRating);
		}

	}


	@SuppressWarnings("unchecked")
	public boolean isRated(Film film, User user) {
		String query = "from Rating r where user.id = ?1 and film.filmId = ?2";
		List<Rating> result = sessionFactory.getCurrentSession().createQuery(query).setParameter("1", user.getId())
				.setParameter("2", film.getFilmId()).list();
		for (Rating r : result) {
			if (r.getUser().equals(user) && r.getFilm().equals(film)) {
				return true;
			}
			return false;
		}
		return false;
	}
}

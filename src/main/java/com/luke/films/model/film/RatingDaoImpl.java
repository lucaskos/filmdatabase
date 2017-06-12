package com.luke.films.model.film;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.user.model.User;

@Component
@Transactional
public class RatingDaoImpl implements RatingDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	FilmsDao filmsDao;

	@Override
	public void rateFilm(Film film, User user, int rating) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Rating filmRating = null;
		if (isRated(film, user)) {
			System.out.println("IS RATED");
			filmRating = getCurrentRating(film, user);
			filmRating.setRating(rating);
			sessionFactory.getCurrentSession().update(filmRating);
		} else {
			filmRating = new Rating(rating, user, film);
			sessionFactory.getCurrentSession().save(filmRating);
		}

	}

	public Rating getCurrentRating(Film film, User user) {
		String query = "from Rating r where user.id = ? and film.filmId = ?";
		Rating r = (Rating) sessionFactory.getCurrentSession().createQuery(query).setParameter(0, user.getId())
				.setParameter(1, film.getFilmId()).list().get(0);
		return r;
	}

	@SuppressWarnings("unchecked")
	public boolean isRated(Film film, User user) {
		String query = "from Rating r where user.id = ? and film.filmId = ?";
		List<Rating> result = sessionFactory.getCurrentSession().createQuery(query).setParameter(0, user.getId())
				.setParameter(1, film.getFilmId()).list();
		for (Rating r : result) {
			if (r.getUser().equals(user) && r.getFilm().equals(film)) {
				return true;
			}
			return false;
		}
		return false;
	}

}

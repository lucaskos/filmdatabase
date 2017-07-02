package com.luke.films.model.film;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.rating.Rating;

@Component("filmsDao")
@Transactional
public class FilmsDaoImpl implements FilmsDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	@Override
	public List<Film> getAllFilms() {
		@SuppressWarnings("unchecked")
		List<Film> list = sessionFactory.getCurrentSession().createQuery("from Film").list();
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void addFilm(Film film) {
		session().saveOrUpdate(film);
	}

	@Override
	public void deleteFilm(Film film) {
		session().delete(film);
	}

	@Override
	public Film getFilmById(int id) {
		String query = "from Film where id = ?1";
		List<?> list = session().createQuery(query).setParameter("1", id).list();
		if (!list.isEmpty()) {
			return (Film) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Film> getFilmsByTitle(String title) {
		String query = "from Film where title = ?1";
		List<Film> list = session().createQuery(query).setParameter("1", title).list();
		return list;
	}

	@Override
	public List<Film> getFilmsByYear(int year) {
		String query = "from Film where year = ?1";
		@SuppressWarnings("unchecked")
		List<Film> list = session().createQuery(query).setParameter("1", year).list();
		if (!list.isEmpty())
			return list;
		else
			return null;
	}
//TODO remove it and work on ratingdao
	@Override
	public void addRating(Rating rating) {
		session().saveOrUpdate(rating);
	}
}

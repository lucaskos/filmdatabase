package com.luke.films.model.cast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

@Component
public class CastDaoImpl implements CastDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public void addActorToFilm(Film film, Actor actor, String role) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		Cast actorFilm = new Cast();
		actorFilm.setFilm(film);
		actorFilm.setActor(actor);
		actorFilm.setRole(role);

		session().save(actorFilm);

		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getCast(Film film) {

		String query = "from ActorFilm where film =?1";

		List<Cast> list = session().createQuery(query).setParameter("1", film).list();
		Map<Actor, String> actorRole = new HashMap<>();
		for (Cast af : list) {
			actorRole.put(af.getActor(), af.getRole());
		}
		// return actorRole;
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<Film, String> getFilmography(Actor actor) {
		String query = "from ActorFilm where actor=?1";

		List<Cast> list = session().createQuery(query).setParameter("1", actor).list();

		Map<Film, String> rolesInFilms = new HashMap<>();
		for (Cast cast : list)
			rolesInFilms.put(cast.getFilm(), cast.getRole());
		return rolesInFilms;
	}

}

package com.luke.films.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

@Transactional
@Component
public class ActorFilmDao {
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
		ActorFilm actorFilm = new ActorFilm();
		actorFilm.setFilm(film);
		actorFilm.setActor(actor);
		actorFilm.setRole(role);

		session().save(actorFilm);

		tx.commit();
	}

	public List<ActorFilm> getActors(Film film) {

		String query = "from ActorFilm where film =?1";

		List<ActorFilm> list = session().createQuery(query).setParameter("1", film).list();
		Map<Actor, String> actorRole = new HashMap<>();
		for (ActorFilm af : list) {
			actorRole.put(af.getActor(), af.getRole());
		}
		//return actorRole;
		return list;
	}

}

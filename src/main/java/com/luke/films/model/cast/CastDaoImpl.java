package com.luke.films.model.cast;

import static org.mockito.Mockito.validateMockitoUsage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.film.Film;

@Component
@Transactional
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

	/*
	 * Checking if in provided film the actor has certain role. If actor and
	 * role not existing in the film new Cast is created. Otherwise update for
	 * the role is made.
	 */
	public void addActorToFilm(Film film, Actor actor, String role) {
		System.out.println("*****Passed parameters : " + film + " : " + actor + " : " + role);
		
		if(actor.getActorFilms().isEmpty()) {
			System.out.println("ACTOR DOESNT HAVE ANY FILMS");
			Cast cast = new Cast(film, actor);
			cast.setRole(role);
			session().save(cast);
		} else {
			
		}		
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getCast(Film film) {
		String query = "from Cast where film =?1";
		List<Cast> list = session().createQuery(query).setParameter("1", film).list();
		// return actorRole;
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getFilmography(Actor actor) {
		String query = "from Cast where actor = ?1";
		List<Cast> list = session().createQuery(query).setParameter("1", actor).list();
		return list;
	}

	public Cast getFilm(Actor actor, Film film) {
		String query = "from Cast where actor = ?1 and film = ?2";
		List<Cast> list = session().createQuery(query).setParameter("1", actor).setParameter("2", film).list();
		System.out.println(list.size());
		return null;
	}

	private Cast isActorInFilm(Film film, List<Cast> castForActor) {
		for (Cast c : castForActor)
			if (c.getFilm().getTitle().toLowerCase().equals(film.getTitle().toLowerCase()))
				return c;
		return null;
	}

	private Cast getFilm(List<Cast> cast, Film film) {
		for (Cast c : cast)
			if (c.getFilm().getTitle().equalsIgnoreCase(film.getTitle())) {
				return c;
			}
		return null;
	}

}

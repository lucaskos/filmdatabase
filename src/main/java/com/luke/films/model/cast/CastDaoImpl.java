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
		Set<Cast> cast = actor.getActorFilms();
		// no film for this actor so we create one
		if (cast.isEmpty()) {
			System.out.println("ACTOR DOESNT HAVE ANY FILMS");
			Cast newCast = new Cast(film, actor);
			newCast.setRole(role);
			session().save(newCast);
		} else {
			Cast c = isInFilm(cast, film);
			if (c != null) {
				// if he is in this film update role
				//so we must iterate and find cast responsible for the
				//given film
				
				c.setRole(role);
				session().update(c);
			} else {
				System.out.println("DOESNT PLAY IN THIS FILM");
				Cast newCast = new Cast(film, actor);
				newCast.setRole(role);
				System.out.println(newCast);
				session().save(newCast);
				// adding new cast and connection to this actor
			}
		}

	}
	//better to return instance of cast responsible for the movie
	private Cast isInFilm(Set<Cast> actorFilms, Film film) {
		for (Cast c : actorFilms)
			if (c.getFilm().getTitle().equalsIgnoreCase(film.getTitle()))
				return c;

		return null;
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

}

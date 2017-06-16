package com.luke.films.model.cast;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Set<Cast> givenActorCast = actor.getActorFilms();
		Cast newCast = new Cast(film, actor);
		// no film for this actor so we create one
		if (givenActorCast.isEmpty()) {
			newCast.setRole(role);
			session().save(newCast);
		} else {
			Cast c = isInFilm(givenActorCast, film);
			if (c != null) {
				// if he is in this film update role
				//so we must iterate and find cast responsible for the
				//given film
				c.setRole(role);
				session().update(c);
			} else {
				newCast.setRole(role);
				System.out.println(newCast);
				session().save(newCast);
				// adding new cast and connection to this actor
			}
		}

	}
	private Cast isInFilm(Set<Cast> actorFilms, Film film) {
		for (Cast cast : actorFilms)
			if (cast.getFilm().getTitle().equalsIgnoreCase(film.getTitle()))
				return cast;
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getCastOfFilm(Film film) {
		String query = "from Cast where film =?1";
		List<Cast> castListOfFilm = session().createQuery(query).setParameter("1", film).list();
		// return actorRole;
		return castListOfFilm;
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getFilmographyOfActor(Actor actor) {
		String query = "from Cast where actor = ?1";
		List<Cast> castListOfActor = session().createQuery(query).setParameter("1", actor).list();
		return castListOfActor;
	}

}

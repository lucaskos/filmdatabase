package com.luke.films.model;

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
	
	
	private Session session(){
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
	
}

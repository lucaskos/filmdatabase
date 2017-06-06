package com.luke.films.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ActorFilmDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private ActorFilm actorFilm;
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public void setActor(Film film, Actor actor){
		actorFilm = new ActorFilm();
		actorFilm.setFilm(film);
		actorFilm.setActor(actor);
		actorFilm.setRole("null"); //TODO null for now
		
		session().save(actorFilm);
	}
}

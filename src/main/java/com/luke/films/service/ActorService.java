package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.actor.ActorDao;
import com.luke.films.model.cast.CastDao;

@Component
public class ActorService {

	@Autowired
	private ActorDao actorDao;
	
	public List<Actor> getAllActors(){
		return actorDao.getAllActors();
	}
	
	public void addActor(Actor actor) {
		actorDao.addActor(actor);
	}
	//TODO add roles here under Security
	@PreAuthorize(value = "hasRole('USER_PREMIUM')")
	public void deleteActor(Actor actor) {
		actorDao.deleteActor(actor);
	}
	
	public Actor getActorByName(Actor actor) {
		return actorDao.getActorByName(actor);
	}
	
	public Actor getActorById(int id) {
		return actorDao.getActorById(id);
	}
	
}

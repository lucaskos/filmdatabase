package com.luke.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.luke.films.model.actor.Actor;
import com.luke.films.model.actor.ActorDao;

@Component
public class ActorService {

	@Autowired
	private ActorDao actorDao;
	
	public List<Actor> getAllActors(){
		return actorDao.getAllActors();
	}
	@Secured("ROLE_USER")
	public void addActor(Actor actor) {
		actorDao.addActor(actor);
	}
	//TODO add roles here under Security
	public void deleteActor(Actor actor) {
		actorDao.deleteActor(actor);
	}
	
	
}

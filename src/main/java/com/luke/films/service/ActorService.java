package com.luke.films.service;

import java.util.List;

import com.luke.films.model.actor.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.luke.films.model.actor.ActorDao;


@Component
public class ActorService {

	@Autowired
	private ActorDao actorDao;

	public List<Person> getAllActors() {
		return actorDao.getAllActors();
	}

	public void addActor(Person person) {
		actorDao.addActor(person);
	}

	// TODO add roles here under Security
	//@PreAuthorize(value = "hasRole('USER_PREMIUM')")
	public void deleteActor(Person person) {
		actorDao.deleteActor(person);
	}

	public Person getActorByName(Person person) {
		return actorDao.getActorByName(person);
	}

	public Person getActorById(int id) {
		return actorDao.getActorById(id);
	}

}

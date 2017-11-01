package com.luke.films.model.actor;

import java.util.List;

public interface ActorDao {

	List<Person> getAllActors();
	
	void addActor(Person person);
	
	void deleteActor(Person person);
	
	Person getActorByName(Person person);
	
	Person getActorById(int id);
	
	
	
}

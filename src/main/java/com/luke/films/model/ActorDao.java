package com.luke.films.model;

import java.util.List;

public interface ActorDao {

	List<Actor> getAllActors();
	
	void addActor(Actor actor);	
	
	void deleteActor(Actor actor);
}

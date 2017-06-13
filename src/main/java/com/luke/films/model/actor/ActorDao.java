package com.luke.films.model.actor;

import java.util.List;

public interface ActorDao {

	List<Actor> getAllActors();
	
	Actor getActor(Actor actor);
	
	void addActor(Actor actor);	
	
	void deleteActor(Actor actor);
	
	Actor getActorByName(Actor actor);
	
}

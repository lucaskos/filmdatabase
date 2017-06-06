package com.luke.films.model.actor;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ActorDaoImpl implements ActorDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addActor(Actor actor) {
		session().save(actor);
	}

	@Override
	public List<Actor> getAllActors() {
		List list = session().createQuery("from Actor").list();
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public void deleteActor(Actor actor) {
		session().delete(actor);
	}
}



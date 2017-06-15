package com.luke.films.model.actor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.luke.films.model.cast.CastDao;

@Component
@Transactional
public class ActorDaoImpl implements ActorDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CastDao castDao;
	
	private Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	@Override
	public void addActor(Actor actor) {
		session().save(actor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> getAllActors() {
		//List<Actor> list = session().createQuery("from Actor").list();
		//TODO have to decide which is better for getting all results
		CriteriaBuilder builder = session().getCriteriaBuilder();
		
		CriteriaQuery<Actor> criteria = builder.createQuery(Actor.class);
		Root<Actor> actorRoot = criteria.from(Actor.class);
		
		criteria.select(actorRoot);
		
		List<Actor> list = session().createQuery(criteria).getResultList();
		
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public void deleteActor(Actor actor) {
		session().delete(actor);
	}

	@Override
	public Actor getActor(Actor actor) {
		//TODO solve this
		return new Actor();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Actor getActorByName(Actor actor) {
		List<Actor> list = session().createQuery("from Actor where name = ?1").setParameter("1", actor.getName()).list();
		if(list.isEmpty())
			return null;
		else 
			return list.get(0);
	}
	
	public Actor getActorById(int id) {
		Actor actor = session().get(Actor.class, id);
		return actor;
	}
}



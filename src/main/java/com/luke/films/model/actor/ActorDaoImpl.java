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

@Component
@Transactional
public class ActorDaoImpl implements ActorDao {

	@Autowired
	private SessionFactory sessionFactory;

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

	@Override
	public List<Actor> getAllActors() {
		CriteriaBuilder builder = session().getCriteriaBuilder();

		CriteriaQuery<Actor> criteria = builder.createQuery(Actor.class);
		Root<Actor> actorRoot = criteria.from(Actor.class);

		criteria.select(actorRoot);

		List<Actor> listOfAllActors = session().createQuery(criteria).getResultList();

		if (!listOfAllActors.isEmpty())
			return listOfAllActors;
		return null;
	}

	@Override
	public void deleteActor(Actor actor) {
		session().delete(actor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Actor getActorByName(Actor actor) {
		List<Actor> listOfActorsByName = session().createQuery("from Actor where name = ?1")
				.setParameter("1", actor.getName()).list();
		if (listOfActorsByName.isEmpty())
			return null;
		else
			return listOfActorsByName.get(0);
	}

	public Actor getActorById(int primaryKey) {
		Actor actorById = session().get(Actor.class, primaryKey);
		return actorById;
	}
}

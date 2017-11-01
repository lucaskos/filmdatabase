package com.luke.films.model.actor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	public void addActor(Person person) {
		session().save(person);
	}

	@Override
	public List<Person> getAllActors() {
		CriteriaBuilder builder = session().getCriteriaBuilder();

		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		Root<Person> actorRoot = criteria.from(Person.class);

		criteria.select(actorRoot);

		List<Person> listOfAllPeople = session().createQuery(criteria).getResultList();

		if (!listOfAllPeople.isEmpty())
			return listOfAllPeople;
		return null;
	}

	@Override
	public void deleteActor(Person person) {
		session().delete(person);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Person getActorByName(Person person) {
		List<Person> listOfActorsByName = session().createQuery("from Person where name = ?1")
				.setParameter("1", person.getFirstName()).list();
		if (listOfActorsByName.isEmpty())
			return null;
		else
			return listOfActorsByName.get(0);
	}

	public Person getActorById(int primaryKey) {
		Person personById = session().get(Person.class, primaryKey);
		return personById;
	}
}

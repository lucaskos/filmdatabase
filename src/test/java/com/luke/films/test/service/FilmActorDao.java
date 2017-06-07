package com.luke.films.test.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.model.ActorFilm;
import com.luke.films.model.actor.ActorDao;
import com.luke.films.model.film.FilmsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmActorDao {

	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private ActorDao actorDao;
	
	@Autowired
	private FilmsDao filmDao;
	
	public Session session(){
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}
	@Test
	public void getAllActors() {
		session().beginTransaction();

		String query = "from ActorFilm";
		
		List<ActorFilm> list = session().createQuery(query).list();
		System.out.println(list.size());
		for(ActorFilm af : list){
			System.out.println(af.getFilm());
			System.out.println(af.getActor());
			System.out.println(af.getRole());
		
		}
	}
}

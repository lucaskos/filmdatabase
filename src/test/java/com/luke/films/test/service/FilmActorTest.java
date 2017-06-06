package com.luke.films.test.service;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.model.Actor;
import com.luke.films.model.ActorDao;
import com.luke.films.model.ActorFilm;
import com.luke.films.model.Film;
import com.luke.films.model.FilmsDao;
import com.luke.user.model.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmActorTest {

	
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
	
	@Before
	public void removeAllRows(){
		List<Actor> actorList = actorDao.getAllActors();
		if(actorList != null)
			for(Actor a : actorList)
				actorDao.deleteActor(a);
		
		List<Film> filmList = filmDao.getAllFilms();
		if(filmList != null)
			for(Film f : filmList)
				filmDao.deleteFilm(f);
	}
	
	@Test
	public void test(){
		Film film = new Film("Silence of the lamb", 1990);
		
		Actor actor =  new Actor("Anthony Hopkins");
		
		Actor actor2 = new Actor("Jodie Foster");
		
		ActorFilm actorFilm = new ActorFilm();
		actorFilm.setFilm(film);
		actorFilm.setActor(actor);
		actorFilm.setRole("Hannibal");
		
		
		
		ActorFilm actorFilm1 = new ActorFilm();
		actorFilm1.setFilm(film);
		actorFilm1.setActor(actor2);
		actorFilm1.setRole("Scarlet");
		Transaction tx = sessionFactory.openSession().beginTransaction();
		
		Session session = session();
		
		session.save(actorFilm);
		session.save(actorFilm1);
		
		
		tx.commit();
		//sessionFactory.getCurrentSession().merge(actorFilm1);
		//session().merge(actorFilm);
		

		
		
		
		
	}

}

package com.luke.films.test.service;

import java.util.List;
import java.util.Map;
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
import com.luke.films.model.actor.Actor;
import com.luke.films.model.actor.ActorDao;
import com.luke.films.model.cast.Cast;
import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.model.user.UserDao;
import com.luke.films.service.CastService;

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
	
	@Autowired
	private CastService castService;
	
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
		Session session = session();
		Cast actorFilm = new Cast();
		actorFilm.setFilm(film);
		actorFilm.setActor(actor);
		actorFilm.setRole("Hannibal");
		
		
		
		Cast actorFilm1 = new Cast();
		actorFilm1.setFilm(film);
		actorFilm1.setActor(actor2);
		actorFilm1.setRole("Scarlet");
		session.save(actorFilm1);
		
		Transaction tx = sessionFactory.openSession().beginTransaction();
		
		session.save(actorFilm);
		session.save(actorFilm1);
		
		Map<Film, String> filmography = castService.getFilmography(actor);
		for(Map.Entry<Film, String> entry : filmography.entrySet()) {
			System.out.println(entry.getKey().getTitle() + " :AS: " + entry.getValue());
		}
		
		
		tx.commit();
		//sessionFactory.getCurrentSession().merge(actorFilm1);
		//session().merge(actorFilm);
		

		
		
		
		
	}

}

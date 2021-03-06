package com.luke.films.test.service;

import java.util.List;
import java.util.Map;

import com.luke.films.model.actor.Person;
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
import com.luke.films.model.actor.ActorDao;
import com.luke.films.model.cast.Cast;
import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.service.CastService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmPersonTest {

	
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
		List<Person> personList = actorDao.getAllActors();
		if(personList != null)
			for(Person a : personList)
				actorDao.deleteActor(a);
		
		List<Film> filmList = filmDao.getAllFilms();
		if(filmList != null)
			for(Film f : filmList)
				filmDao.deleteFilm(f);
	}
	
	@Test
	public void test(){
		Film film = new Film("silence of the lambs", 1989, "silence of the lambs");
		Film filmTest = new Film("Test film", 1990, "Test description");
		Person person =  new Person("Anthony Hopkins");
		
		Person person2 = new Person("Jodie Foster");
		Session session = session();
		Cast actorFilm = new Cast();
		actorFilm.setFilm(film);
		actorFilm.setPerson(person);
		actorFilm.setRole("Hannibal");
		
	
		
		Cast actorFilm1 = new Cast();
		actorFilm1.setFilm(film);
		actorFilm1.setPerson(person2);
		actorFilm1.setRole("Scarlet");
		session.save(actorFilm1);
		
		Transaction tx = sessionFactory.openSession().beginTransaction();
		
		session.save(actorFilm);
		session.save(actorFilm1);
		
		Map<Film, String> filmography = castService.getFilmography(person);
		for(Map.Entry<Film, String> entry : filmography.entrySet()) {
			System.out.println(entry.getKey().getTitle() + " :AS: " + entry.getValue());
		}
		session().save(filmTest);
		
		Person personTest = new Person("Test person");
		session().save(personTest);
		
		System.out.println(actorDao.getActorByName(personTest));
		
		tx.commit();
		//sessionFactory.getCurrentSession().merge(actorFilm1);
		//session().merge(actorFilm);
		

		
		
		
		
	}

}

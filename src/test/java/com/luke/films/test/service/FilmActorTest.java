package com.luke.films.test.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matcher;
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
import com.luke.films.dao.Actor;
import com.luke.films.dao.ActorDao;
import com.luke.films.dao.Film;
import com.luke.films.dao.FilmsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmActorTest {

	@Autowired
	private ActorDao actorDao;

	@Autowired
	FilmsDao filmsDao;

	@Before
	public void removeAllEntities() {
		List<Actor> allActors = actorDao.getAllActors();
		if (allActors != null)
			for (Actor a : allActors)
				actorDao.deleteActor(a);

		List<Film> allFilms = filmsDao.getAllFilms();
		if (allFilms != null)
			for (Film f : allFilms)
				filmsDao.deleteFilm(f);
	}

	@Test
	public void gettingActorFilm() {

		// assertNull(actorDao.getAllActors());
		assertNull(filmsDao.getAllFilms());

		int numberOfEntities = 10;

		createFilmList(numberOfEntities);

		Film film = filmsDao.getAllFilms().get(0);
		System.out.println(film.getActors());
		
	}

	private void createFilmList(int numberOfEntities) {
		Set<Actor> actorSet = new HashSet<>();
		Actor actor;
		Set<Film> filmsSet = new HashSet<>();
		for (int i = 0; i < numberOfEntities; i++) {
			filmsDao.addFilm(new Film("Title: " + i, 1900 + i));
			actor = new Actor();
			actor.setName("NAME: " + i);
			actorDao.addActor(actor);
			actorSet.add(actor);
		}
		Film film = filmsDao.getAllFilms().get(0);
		
		filmsSet.add(film);
		
		film.setActors(actorSet);
		
		
		
	}

	private void createActorList(int numberOfEntities) {
		for (int i = 0; i < numberOfEntities; i++) {
			Actor actor = new Actor();
			actor.setName("NAME: " + i);
			actorDao.addActor(actor);
		}

	}

}

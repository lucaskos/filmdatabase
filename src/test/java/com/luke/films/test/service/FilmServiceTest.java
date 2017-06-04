package com.luke.films.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.dao.Film;
import com.luke.films.dao.FilmsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmServiceTest {

	@Autowired
	FilmsDao filmsDao;

	@Test
	public void filmTest() {
		Film film1 = new Film("film1", 1000);
		Film film2 = new Film("film2", 2000);
		Film film3 = new Film("film3", 2000);

		// list should be empty
		assertNull(filmsDao.getAllFilms());

		// adding test Film objects to db
		filmsDao.addFilm(film1);
		filmsDao.addFilm(film2);
		filmsDao.addFilm(film3);

		List<Film> allFilms = filmsDao.getAllFilms();
		
		//check if film equals the one created in db
		assertEquals(film1, allFilms.get(0));
		assertEquals(allFilms.get(1), film2);

		// list should NOT be empty
		assertNotNull(allFilms);
		
		//checking if films added to db have the same title as one in db
		assertEquals(film1, filmsDao.getFilmByTitle(film1.getTitle()));
		assertEquals(film2, filmsDao.getFilmByTitle(film2.getTitle()));

		List<Film> filmsByYearFromDb = filmsDao.getFilmsByYear(film3.getYear());
		
		assertNotNull(filmsByYearFromDb);
		List<Film> filmsByYear = new ArrayList<>();
		filmsByYear.add(film2);
		filmsByYear.add(film3);
		
		//checking if 2 films of the same year equal those in db
		assertEquals(filmsByYearFromDb, filmsByYear);
		
		// removing the films by Film object
		for (Film f : allFilms)
			filmsDao.deleteFilm(f);
		
		// adding test Film objects to db again
		filmsDao.addFilm(film1);
		filmsDao.addFilm(film2);
		
		allFilms = filmsDao.getAllFilms();
		
		//removing films by Film id
		for(Film f : allFilms)
			filmsDao.deleteById(f.getId());
		
		assertNull(filmsDao.getAllFilms());
		
	}

}

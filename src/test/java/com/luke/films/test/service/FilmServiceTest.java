package com.luke.films.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
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
import com.luke.films.model.cast.Cast;
import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.service.FilmService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmServiceTest {

	@Autowired
	FilmService filmsService;
	static Logger log = Logger.getLogger(FilmServiceTest.class.getName());
	@Before
	public void removeFilms() {
		List<Film> allFilms = filmsService.getAllFilms();
		if(allFilms != null)
			for(Film f : allFilms)
				filmsService.deleteFilm(f);
	}
	
	
	@Test
	public void filmTest() {
		
		String description = "Test description";
		
		Film film1 = new Film("film1", 1000, description);
		Film film2 = new Film("film2", 2000, description);
		Film film3 = new Film("film3", 2000, description);

		// list should be empty
		assertNull(filmsService.getAllFilms());

		// adding test Film objects to db
		filmsService.addFilm(film1);
		filmsService.addFilm(film2);
		filmsService.addFilm(film3);

		List<Film> allFilms = filmsService.getAllFilms();


		// list should NOT be empty
		assertNotNull(allFilms);

		// checking if films added to db have the same title as one in db
		assertEquals(film1, filmsService.getFilmByTitle(film1.getTitle()));
		assertEquals(film2, filmsService.getFilmByTitle(film2.getTitle()));
		assertEquals(film3, filmsService.getFilmByTitle(film3.getTitle()));

		List<Film> filmsByYearFromDb = filmsService.getFilmsByYear(film3.getYear());

		assertNotNull(filmsByYearFromDb);
		List<Film> filmsByYear = new ArrayList<>();
		filmsByYear.add(film2);
		filmsByYear.add(film3);

		// checking if 2 films of the same year equal those in db
		assertEquals(filmsByYearFromDb, filmsByYear);

		allFilms = filmsService.getAllFilms();
		assertNotNull(allFilms);
		
		Film filmByTitle = filmsService.getFilmByTitle(film1.getTitle());
		
		assertEquals(film1, filmByTitle);
		
		for(Film f : allFilms)
			filmsService.deleteFilm(f);
	
		assertNull(filmsService.getAllFilms());
		
	}

	private List<Film> createFilms(int filmsNumber) {
		List<Film> list = new ArrayList<>();

		for (int i = 0; i < filmsNumber; i++) {
			Film temp = new Film();
			temp.setFilmId(i);
			temp.setTitle("TITLE :" + i);
			temp.setYear(1900 + i);
			temp.setDescription("Test description for film");
			list.add(temp);
		}
		return list;
	}

}

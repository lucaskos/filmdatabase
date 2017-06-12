package com.luke.films.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	@Before
	public void removeFilms() {
		List<Film> allFilms = filmsService.getAllFilms();
		if(allFilms != null)
			for(Film f : allFilms)
				filmsService.deleteFilm(f);
	}
	
	
	@Test
	public void filmTest() {
		
		Film film1 = new Film("film1", 1000);
		Film film2 = new Film("film2", 2000);
		Film film3 = new Film("film3", 2000);

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

		List<Film> filmsByYearFromDb = filmsService.getFilmsByYear(film3.getYear());

		assertNotNull(filmsByYearFromDb);
		List<Film> filmsByYear = new ArrayList<>();
		filmsByYear.add(film2);
		filmsByYear.add(film3);

		// checking if 2 films of the same year equal those in db
		assertEquals(filmsByYearFromDb, filmsByYear);

		// removing the films by Film object
		for (Film f : allFilms)
			filmsService.deleteFilm(f);

		// adding test Film objects to db again
		filmsService.addFilm(film1);
		filmsService.addFilm(film2);

		allFilms = filmsService.getAllFilms();

		// removing films by Film id
		for (Film f : allFilms)
			filmsService.deleteById(f.getFilmId());

		assertNull(filmsService.getAllFilms());

		int filmsNumber = 50;
		List<Film> films = createFilms(filmsNumber);

		// adding filmsNumber of films to db
		for (Film f : films)
			filmsService.addFilm(f);
		
		List<Film> findFilms = filmsService.findFilms(5, 10);
		
		for(Film f : findFilms)
			System.out.println(f);
		
		// removing everything from db
		for (Film f : films)
			filmsService.deleteFilm(f);
		
		assertNull(filmsService.getAllFilms());

		// findFilm(films, 4, 10);
	}

	private List<Film> createFilms(int filmsNumber) {
		List<Film> list = new ArrayList<>();

		for (int i = 0; i < filmsNumber; i++) {
			Film temp = new Film();
			temp.setFilmId(i);
			temp.setTitle("TITLE :" + i);
			temp.setYear(1900 + i);
			list.add(temp);
		}
		return list;
	}

}

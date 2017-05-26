package com.luke.films.test.dao;

import java.util.List;

import org.junit.Assert;
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
public class FilmDaoTest {

	@Autowired
	public FilmsDao filmsDao;

	@Test
	public void getFilms() {
		List<Film> allFilms = filmsDao.getFilms();
		
		for(Film f : allFilms)
			filmsDao.deleteFilm(f);
		
		Film f1 = new Film("The wuthering heights", 1988, "not a great movie but ok");
		filmsDao.deleteFilm(f1);
		filmsDao.addFilm(f1);

		System.out.println("*******Select by ID: " + filmsDao.deleteById(f1.getId()));
		
		System.out.println("\n\n\nBY ID : " + filmsDao.deleteById(f1.getId()));
	}

}

package com.luke.films.test.dao;

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
@ContextConfiguration(classes = {ApplicationConfigCore.class, HibernateConfig.class})
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class FilmDaoTest {
	
	@Autowired
	public FilmsDao filmsDao;
	
	@Test
	public void getFilms(){
			
		List<Film> films = filmsDao.getFilms();
		
		for(Film f : films) 
			System.out.println(f);
		
	}

}

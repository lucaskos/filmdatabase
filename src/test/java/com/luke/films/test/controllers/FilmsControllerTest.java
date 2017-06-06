package com.luke.films.test.controllers;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.controllers.FilmsController;
import com.luke.films.model.film.Film;
import com.luke.films.service.FilmsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfigCore.class, HibernateConfig.class})
@ActiveProfiles("test")
@WebAppConfiguration("WebContent")
public class FilmsControllerTest {
	
	
	
	@Test
	public void pagedFilms(){
		List<Film> expectedFilms = createFilmList(50);
		FilmsService mockService = mock(FilmsService.class);
		
	
	}

	private List<Film> createFilmList(int filmsNumber) {
		List<Film> list = null;
		Film temp = new Film();
		for(int i = 0; i < filmsNumber; i++){
			temp.setFilmId(i);
			temp.setTitle("TITLE :" +i);
			temp.setYear(1900 + i);
		}
		return list;
	}

}

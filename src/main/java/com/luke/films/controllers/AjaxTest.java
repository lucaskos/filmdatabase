package com.luke.films.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;

@Controller
public class AjaxTest {
	
	@Autowired
	FilmsDao filmsDao;

	@ResponseBody
	@RequestMapping(value = "/{filmId}", method = RequestMethod.POST)
	public void getSearchResultViaAjax(@RequestParam("filmId") int filmId, @RequestParam("rating") int rating) {

		System.out.println(rating);

		Film filmById = filmsDao.getFilmById(filmId);
		System.out.println(filmById);
		
		System.out.println("rating for movie is :" +rating);
	}
	
}

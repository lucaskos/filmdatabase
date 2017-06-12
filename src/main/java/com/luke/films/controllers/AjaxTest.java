package com.luke.films.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.model.film.RatingDao;
import com.luke.films.service.UserService;

@Controller
public class AjaxTest {
	
	@Autowired
	FilmsDao filmsDao;
	
	@Autowired
	RatingDao ratingDao;
	
	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping(value = "/{filmId}", method = RequestMethod.POST)
	public void getSearchResultViaAjax(@RequestParam("filmId") int filmId, @RequestParam("rating") int rating) {

		System.out.println(rating);
		Film filmById = filmsDao.getFilmById(filmId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String name = auth.getName();
		
		ratingDao.rateFilm(filmById, userService.getUser(name), rating);
		System.out.println(filmsDao.getRating(filmById));
		System.out.println(filmById.getRating().size());
		System.out.println("rating for movie is :" +rating);
		
		
	}
	
}

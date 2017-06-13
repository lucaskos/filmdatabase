package com.luke.films.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luke.films.model.film.Film;
import com.luke.films.model.rating.RatingDao;
import com.luke.films.service.FilmService;
import com.luke.films.service.UserService;

@Controller
public class FilmsController {

	@Autowired
	private FilmService filmsService;
	@Autowired
	private UserService userService;
	@Autowired
	private RatingDao ratingDao;

	@RequestMapping(value = "/filmslist", method = RequestMethod.GET)
	public String showBookList(Model model) {
		model.addAttribute("film", filmsService.getAllFilms());
		return "filmslist";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String filmAdded(@Valid Film film, BindingResult results) {
		if (results.hasErrors()) {
			return "addfilm";
		}
		filmsService.addFilm(film);
		return "redirect:/filmslist";
	}

	@RequestMapping(value = "/{filmId}", method = RequestMethod.POST)
	public void getSearchResultViaAjax(@RequestParam("filmId") int filmId, @RequestParam("rating") int rating) {
		System.out.println("Passed variable " + filmId + " : " + rating);
		Film filmById = filmsService.getFilmById(filmId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		ratingDao.rateFilm(filmById, userService.getUser(name), rating);
	}

	@RequestMapping(value = "/removeFilm", method = RequestMethod.GET)
	public String removeFilm(@RequestParam("filmId") String filmId) {
		System.out.println("DELETE FILM");
		System.out.println(filmId);
		filmsService.deleteById(Integer.valueOf(filmId));
		return "redirect:/filmslist";
	}

}

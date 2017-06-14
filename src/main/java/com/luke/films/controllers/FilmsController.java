package com.luke.films.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
	public String filmAdded(@Valid Film film, BindingResult results, HttpServletRequest request) {
		if (results.hasErrors()) {
			return "addfilm";
		}
		Film sessionFilm = (Film) request.getSession().getAttribute("film");

		if (sessionFilm != null) {
			film.setFilmId(sessionFilm.getFilmId());

			filmsService.addFilm(film);
			System.out.println(sessionFilm.getFilmId());
		} else {
			filmsService.addFilm(film);
		}
		request.getSession().removeAttribute("film");
		return "redirect:/filmslist";
	}

	@RequestMapping(value = "/{filmId}", method = RequestMethod.POST)
	public void getSearchResultViaAjax(@RequestParam("filmId") int filmId, @RequestParam("rating") int rating) {
		Film filmById = filmsService.getFilmById(filmId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		ratingDao.rateFilm(filmById, userService.getUser(name), rating);
	}

	@RequestMapping(value = "/removeFilm", method = RequestMethod.GET)
	public String removeFilm(@RequestParam("filmId") String filmId) {
		filmsService.deleteById(Integer.valueOf(filmId));
		return "redirect:/filmslist";
	}

	@RequestMapping(value = "/editfilm", method = RequestMethod.GET)
	public String editFilm(@RequestParam("filmId") String filmId, Model model, HttpSession session) {

		session.setAttribute("film", filmsService.getFilmById(Integer.valueOf(filmId)));

		return "redirect:/addfilm";
	}

	@RequestMapping(value = "/addfilm")
	public String addFilm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Film film = (Film) session.getAttribute("film");

		if (film != null)
			model.addAttribute("film", film);
		else {
			session.removeAttribute("film");
			model.addAttribute("film", new Film());
		}
		return "addfilm";
	}

}

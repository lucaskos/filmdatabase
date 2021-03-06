package com.luke.films.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.luke.films.cache.DictionaryDao;
import com.luke.films.common.ControllerConstants;
import com.luke.films.common.QualifierConstants;
import com.luke.films.model.comment.CommentDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luke.films.model.film.Film;
import com.luke.films.service.FilmService;
import com.luke.films.service.UserService;

import java.util.List;

@Controller
public class FilmsController {

	private final String updateButtonText = "Update Film";

	private static final Logger logger = Logger.getLogger(FilmsController.class);

	@Autowired
	private FilmService filmsService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	@Qualifier(QualifierConstants.GENRES_DIC_DAO)
	private DictionaryDao dictionaryDao;

	@RequestMapping(value = "/filmlist", method = RequestMethod.GET)
	public String showAllFilms(Model model) {
		model.addAttribute(ControllerConstants.FILM, filmsService.getAllFilms());
		logger.info("The number of films available is: " + filmsService.getAllFilms().size());

		List all = dictionaryDao.getAll();

		return ControllerConstants.FILM_LIST;
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String filmAdded(@Valid Film film, BindingResult results, HttpServletRequest request) {
		if (results.hasErrors()) {
			logger.error("The error exists during film creation.");
			return "addfilm";
		}
		Film sessionFilm = (Film) request.getSession().getAttribute(ControllerConstants.FILM);

		if (sessionFilm != null) {
			logger.info("Film " + sessionFilm + " will be added to database.");
			film.setFilmId(sessionFilm.getFilmId());

			filmsService.addFilm(film);
			System.out.println(sessionFilm.getFilmId());
		} else {
			filmsService.addFilm(film);
		}
		request.getSession().removeAttribute(ControllerConstants.FILM);
		return ControllerConstants.REDIRECT+ ControllerConstants.FILM_LIST;
	}

	@RequestMapping(value = "/{filmId}", method = RequestMethod.POST)
	public void getSearchResultViaAjax(@RequestParam("filmId") int filmId, @RequestParam("rating") int rating) {
		Film filmById = filmsService.getFilmById(filmId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println("Authentication : " + auth.getPrincipal());
		filmsService.rateFilm(filmById, userService.getUser(name), rating);
	}
	/**
	 * Removal of the film object had to be done through GET method.
	 * Jsp not allows to use DELETE method.
	 * @param filmId String representation of filmID.
	 * @return redirect to list of all films after deletion.
	 */
	@RequestMapping(value = "/removeFilm", method = RequestMethod.GET)
	public String removeFilm(@RequestParam("filmId") String filmId) {
		logger.info("Deleting film of id :" + filmId);
		filmsService.deleteById(Integer.valueOf(filmId));
		return ControllerConstants.REDIRECT+ ControllerConstants.FILM_LIST;
	}

	@RequestMapping(value = "/editfilm", method = RequestMethod.GET)
	public String editFilm(@RequestParam("filmId") String filmId, Model model, HttpSession session) {

		session.setAttribute("film", filmsService.getFilmById(Integer.valueOf(filmId)));

		return "redirect:/addfilm";
	}

	@RequestMapping(value = "/addfilm")
	public String addFilm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Film film = (Film) session.getAttribute(ControllerConstants.FILM);

		if (film != null)
			model.addAttribute(ControllerConstants.FILM, film);
		else {
			session.removeAttribute(ControllerConstants.FILM);
			model.addAttribute(ControllerConstants.FILM, new Film());
		}
		return "addfilm";
	}
}

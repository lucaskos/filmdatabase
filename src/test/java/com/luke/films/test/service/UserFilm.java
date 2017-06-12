package com.luke.films.test.service;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.films.model.actor.ActorDao;
import com.luke.films.model.film.Film;
import com.luke.films.model.film.FilmsDao;
import com.luke.films.model.rating.Rating;
import com.luke.films.model.rating.RatingDao;
import com.luke.films.model.user.User;
import com.luke.films.model.user.role.Role;
import com.luke.films.model.user.role.RoleDao;
import com.luke.films.service.FilmService;
import com.luke.films.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "test")
public class UserFilm {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private ActorDao actorDao;
	@Autowired
	UserService userService;
	@Autowired
	FilmService filmsService;

	@Autowired
	private FilmsDao filmDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	RatingDao ratingDao;

	public Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	@Test
	public void test() {
		Film film1 = new Film("film1", 1000);
		Film film2 = new Film("film2", 2000);
		Film film3 = new Film("film3", 2000);

		// filmsService.addFilm(film1);
		// filmsService.addFilm(film2);
		// filmsService.addFilm(film3);

		assertNotNull(filmsService.getAllFilms());

		User u1 = new User("lucaskos", "lucas7", "kosmala.luke@gmail.com");
		User u2 = new User("lucaskos1", "lucas71", "kosmala1.luke@gmail.com");
		Role role = new Role("ROLE_USER");
		Set<Role> ur = new HashSet<>();
		ur.add(role);
		u1.setUsersRoles(ur);
		u2.setUsersRoles(ur);
		// session().save(role);
		 //userService.createUser(u2);
		int rating = 3;
		// assertNotNull(userService.getAllUsers());
		Film filmById = filmsService.getFilmById(343);
		ratingDao.rateFilm(filmsService.getFilmById(343), userService.getUser("lucaskos"), rating);
		ratingDao.rateFilm(filmsService.getFilmById(343), userService.getUser("lucaskos1"), rating-1);
	}
}

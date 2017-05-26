package com.luke.films.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("filmsDao")
@Transactional
public class FilmsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public List<Film> getFilms() {
		@SuppressWarnings("unchecked")
		List<Film> list = sessionFactory.getCurrentSession().createQuery("from Film").list();
		return list;
	}
	
	public void addFilm(Film film) {
		sessionFactory.getCurrentSession().save(film);
	}
	
	
}

package com.luke.films.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("filmsDao")
@Transactional
public class FilmsDaoImpl implements FilmsDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.luke.films.dao.FilmsDao#getFilms()
	 */
	@Override
	public List<Film> getAllFilms() {
		@SuppressWarnings("unchecked")
		List<Film> list = sessionFactory.getCurrentSession().createQuery("from Film").list();
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void addFilm(Film film) {
		sessionFactory.getCurrentSession().save(film);
	}

	@Override
	public void deleteFilm(Film film) {
		sessionFactory.getCurrentSession().delete(film);
	}

	@Override
	public void deleteById(int id) {
		Film f = getFilmById(id);
		sessionFactory.getCurrentSession().delete(f);
	}

	@Override
	public Film getFilmById(int id) {
		String query = "from Film where id = ?1";
		List<?> list = sessionFactory.getCurrentSession().createQuery(query).setParameter("1", id).list();
		if (!list.isEmpty()) {
			return (Film) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Film getFilmByTitle(String title) {
		String query = "from Film where title = ?1";
		List<?> list = sessionFactory.getCurrentSession().createQuery(query).setParameter("1", title).list();
		if (!list.isEmpty())
			return (Film) list.get(0);
		else
			return null;
	}

	@Override
	public List<Film> getFilmsByYear(int year) {
		String query = "from Film where year = ?1";
		@SuppressWarnings("unchecked")
		List<Film> list = sessionFactory.getCurrentSession().createQuery(query).setParameter("1", year).list();
		if (!list.isEmpty())
			return list;
		else
			return null;
	}

	//TO DO find next @count films starting with @id, pagination
	@Override
	public List<Film> findFilms(int id, int count) {
		
		String countQ = "select count(*) from Film";
		Query countQuery = sessionFactory.getCurrentSession().createQuery(countQ);
		Long countResults = (Long) countQuery.uniqueResult();
		
		int lastPageNumber = (int) Math.ceil(countResults/count);
		
		System.out.println(lastPageNumber);
		
		Query<Film> query = sessionFactory.getCurrentSession().createQuery("From Film");
		query.setFirstResult(id);
		query.setMaxResults(count);
		List<Film> list = query.list();
		return list;
	}

}

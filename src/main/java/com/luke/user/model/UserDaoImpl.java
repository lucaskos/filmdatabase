package com.luke.user.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private NamedParameterJdbcTemplate jdbc;

	/*
	 * We can't directly autwired field of dataSource because it must be passed
	 * to jdbc template in order to work
	 */
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void createUser(User user) {
		
			Set<Role> ur = new HashSet<>();
			ur.add(new Role("ROLE_USER"));
			user.setUsersRoles(ur);
			

		session().save(user);
		// return jdbc.update(sql , params) == 1;
	}

	public boolean checkUserExist(User user) {

		String sql = "SELECT COUNT(*) FROM users WHERE username=:username";

		return jdbc.queryForObject(sql, new MapSqlParameterSource("username", user.getUsername()), Integer.class) > 0;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUsers() {
		List<User> list = session().createQuery("from User").list();
		if(list.isEmpty())
			return null;
		return list;
	}

	@Transactional
	public User getUser(String username) {
		User activeUser = new User();
		String query = "from User where username = ?1";
		List<?> list = session().createQuery(query).setParameter("1", username).list();
		if (!list.isEmpty()) {
			activeUser = (User) list.get(0);
		} else {
			return null;
		}
		return activeUser;
	}

	@Override
	public void removeUser(User user) {
		User temp = getUser(user.getUsername());
		session().delete(temp);
		
	}
}

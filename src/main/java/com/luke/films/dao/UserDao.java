package com.luke.films.dao;

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
@Transactional
public class UserDao {

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
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", user.getPassword());
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		String sql = "INSERT INTO users (username, password, email, enabled) values (:username, :password, :email, :enabled)";
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		List list = session().createQuery("from Role").list();
		Set<Role> ur = new HashSet<>();
		ur.add((Role) list.get(1));//save new user with the permission of ROLE_USER
		user.setUsersRoles(ur);
		
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
		return list;
	}

	public User getUser(String username) {
		User activeUser = new User();
		List<?> list = session().createQuery("from User where username=?").setParameter(0, username).list();
		if (!list.isEmpty()) {
			activeUser = (User) list.get(0);
		}
		return activeUser;
	}
}

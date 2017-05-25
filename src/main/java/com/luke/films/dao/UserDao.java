package com.luke.films.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private NamedParameterJdbcTemplate jdbc;
	
	/*
	 * We can't directly autwired field of dataSource because
	 * it must be passed to jdbc template in order to work
	 */
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public boolean createUser(User user){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", user.getPassword());
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		System.out.println(user);
		String sql = "INSERT INTO users (username, password, email, enabled) values (:username, :password, :email, :enabled)";
		
		return jdbc.update(sql	, params) == 1;
	}
	
	public boolean checkUserExist(User user) {
		
		String sql = "SELECT COUNT(*) FROM users WHERE username=:username";
		
		return jdbc.queryForObject(sql, new MapSqlParameterSource("username", user.getUsername()), Integer.class) > 0;
	}
	
	public List<User> getAllUsers(){
		return session().createQuery("from User").list();
		//return null;
	}
}

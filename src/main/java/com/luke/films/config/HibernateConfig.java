package com.luke.films.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	private final String FILM_MODEL = "com.luke.films.model";
	private final String USER_MODEL = "com.luke.user.model";

	private final String HIBERNATE_DIALECT = "hibernate.dialect";
	private final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private final String HIBERNATE_ID_NEW_GENERATOR_MAPPINGS = "hibernate.id.new_generator_mappings";

	@Autowired(required=true)
	private DataSource dataSource;

	/**
	 * HibernateTemplate - helper class to interact with db. converts hibernate
	 * exceptiopins to data access exceptions. can be used in DAO classes to get
	 * data from db
	 */
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory().getObject());
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] {FILM_MODEL, USER_MODEL});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/**
	 * returns hibernate properties
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, "${hibernate.dialect}");
		properties.put(HIBERNATE_SHOW_SQL, "${hibernate.show_sql}");
		properties.put(HIBERNATE_ID_NEW_GENERATOR_MAPPINGS, "${hibernate.id.new_generator_mappings}");
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}

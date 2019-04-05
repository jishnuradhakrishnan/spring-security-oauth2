package com.oauth2.demo.commons;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RepositorySupport {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	protected DataSource dataSource;

	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	protected DataSource getDataSource() {
		return this.dataSource;
	}
}

package org.certificatic.spring.jdbc.dao.springjdbc;

import java.io.Serializable;

import org.certificatic.spring.jdbc.dao.IGenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import lombok.Getter;

public abstract class GenericSpringJdbcDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	@Autowired
	protected @Getter JdbcTemplate jdbcTemplate;

	@Autowired
	protected @Getter NamedParameterJdbcTemplate namedJdbcTemplate;

}

package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.impl.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.certificatic.spring.jdbc.pratica25.dao.api.ICustomerDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowmapper.CustomerRowMapper;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Customer;
import org.certificatic.spring.jdbc.pratica25.domain.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Profile({ "h2-in-memory", "h2-local" })
public class CustomerSpringJdbcDAO extends GenericSpringJdbcDAO<Customer, Long> implements ICustomerDAO {

	private static final String INSERT_CUSTOMER = "INSERT INTO SPRING_DATA_CUSTOMER_TBL VALUES (null, :name, :lastName)";
	private static final String INSERT_USER = "INSERT INTO SPRING_DATA_USER_TBL VALUES (null, :fkCustomerId, :username, :password)";

	private static final String UPDATE_CUSTOMER = "UPDATE SPRING_DATA_CUSTOMER_TBL SET NAME = :name, LAST_NAME = :lastName WHERE CUSTOMER_ID = :customerId";
	private static final String UPDATE_USER = "UPDATE SPRING_DATA_USER_TBL SET USERNAME = :username, PASSWORD = :password WHERE USER_ID = :userId";

	private static final String SELECT_CUSTOMER = "SELECT * FROM SPRING_DATA_CUSTOMER_TBL WHERE CUSTOMER_ID = :customerId";
	private static final String SELECT_USER_WHERE_CUSTOMER_ID = "SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId";

	private static final String SELECT_ALL_CUSTOMER_USER = "SELECT * FROM SPRING_DATA_CUSTOMER_TBL, SPRING_DATA_USER_TBL WHERE CUSTOMER_ID = FK_CUSTOMER_ID";

	private static final String DELETE_ACCOUNT_TABLE_WHERE_CUSTOMER_ID = "DELETE FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = :customerId";
	private static final String DELETE_USER_WHERE_USER_ID = "DELETE FROM SPRING_DATA_USER_TBL WHERE USER_ID = :userId";
	private static final String DELETE_CUSTOMER_WHERE_CUSTOMER_ID = "DELETE FROM SPRING_DATA_CUSTOMER_TBL WHERE CUSTOMER_ID = :customerId";

	@Override
	public void insert(Customer entity) {

		// INSERT CUSTOMER
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("name", entity.getName());
		parameterSource.addValue("lastName", entity.getLastName());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.namedJdbcTemplate.update(INSERT_CUSTOMER, parameterSource, keyHolder);

		entity.setId(keyHolder.getKey().longValue());

		// INSERT USER
		parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("fkCustomerId", entity.getUser().getCustomer().getId());
		parameterSource.addValue("username", entity.getUser().getUsername());
		parameterSource.addValue("password", entity.getUser().getPassword());

		keyHolder = new GeneratedKeyHolder();

		this.namedJdbcTemplate.update(INSERT_USER, parameterSource, keyHolder);

		entity.getUser().setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Customer entity) {

		// UPDATE CUSTOMER
		Map<String, Object> mapParameters = new HashMap<>();
		mapParameters.put("name", entity.getName());
		mapParameters.put("lastName", entity.getLastName());
		mapParameters.put("customerId", entity.getId());

		this.namedJdbcTemplate.update(UPDATE_CUSTOMER, mapParameters);

		// UPDATE USER
		mapParameters = new HashMap<>();
		mapParameters.put("username", entity.getUser().getUsername());
		mapParameters.put("password", entity.getUser().getPassword());
		mapParameters.put("userId", entity.getUser().getId());

		this.namedJdbcTemplate.update(UPDATE_USER, mapParameters);

	}

	@Override
	public Customer findById(Long id) {
		Customer customer = null;

		// FIND CUSTOMER BY ID
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("customerId", id);

		try {
			/*
			///////////////////////////////////////////////////////////
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("customerId", id);
			
			String username = this.namedJdbcTemplate.queryForObject(
					"SELECT username FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId", paramMap,
					String.class);
			log.info("lol username: {}", username);
			
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("customerId", id);
			
			username = this.namedJdbcTemplate.queryForObject(
					"SELECT username FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId", paramSource,
					String.class);
			log.info("lol username: {}", username);
			
			paramSource = new MapSqlParameterSource().addValue("customerId", id);
			
			User us = this.namedJdbcTemplate.queryForObject(
					"SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId", paramSource,
					new RowMapper<User>() {
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							User usr = new User();
							usr.setId(rs.getLong("USER_ID"));
							usr.setUsername(rs.getString("USERNAME"));
							usr.setPassword(rs.getString("PASSWORD"));
							return usr;
						}
					});
			log.info("lol user: {}", us);
			
			paramSource = new MapSqlParameterSource().addValue("customerId", id);
			
			Map<String, Object> mapResult = this.namedJdbcTemplate.queryForMap(
					"SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId", paramSource);
			
			log.info("lol mapResult: {}", mapResult);
			
			paramMap = new HashMap<>();
			paramMap.put("customerId", id);
			
			mapResult = this.namedJdbcTemplate.queryForMap(
					"SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId", paramMap);
			
			log.info("lol mapResult: {}", mapResult);
			
			paramMap = new HashMap<>();
			paramMap.put("customerId", 0);
			
			List<String> usernames = this.namedJdbcTemplate.queryForList(
					"SELECT username FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > :customerId", paramMap,
					String.class);
			
			log.info("lol usernames: {}", usernames);
			
			paramSource = new MapSqlParameterSource().addValue("customerId", 0);
			
			usernames = this.namedJdbcTemplate.queryForList(
					"SELECT username FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > :customerId", paramSource,
					String.class);
			
			log.info("lol usernames: {}", usernames);
			
			paramMap = new HashMap<>();
			paramMap.put("customerId", 0);
			
			List<Map<String, Object>> data = this.namedJdbcTemplate.queryForList(
					"SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > :customerId", paramMap);
			
			log.info("lol data: {}", data);
			
			paramSource = new MapSqlParameterSource().addValue("customerId", 0);
			
			data = this.namedJdbcTemplate.queryForList(
					"SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > :customerId", paramSource);
			
			log.info("lol data: {}", data);
			
			///////////////////////////////////////////////////////////
			*/

			customer = this.namedJdbcTemplate.queryForObject(SELECT_CUSTOMER, parameterSource,
					new RowMapper<Customer>() {
						@Override
						public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
							Customer customer = new Customer();
							customer.setId(rs.getLong("CUSTOMER_ID"));
							customer.setName(rs.getString("NAME"));
							customer.setLastName(rs.getString("LAST_NAME"));
							return customer;
						}
					});

		} catch (EmptyResultDataAccessException ex) {
			// Cuando se usa queryForObject se espera al menos 1 resultado.
			return null;
		}

		// FIND USER OF CUSTOMER BY CUSTOMER_ID
		parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("customerId", customer.getId());

		User user = this.namedJdbcTemplate.queryForObject(SELECT_USER_WHERE_CUSTOMER_ID, parameterSource,
				(ResultSet rs, int rowNum) -> {
					User u = new User();
					u.setId(rs.getLong("USER_ID"));
					u.setUsername(rs.getString("USERNAME"));
					u.setPassword(rs.getString("PASSWORD"));
					return u;

				});

		customer.setUser(user);
		user.setCustomer(customer);

		return customer;
	}

	@Override
	public Customer delete(Long id) {
		Customer customer = this.findById(id);
		return this.delete(customer);
	}

	@Override
	public Customer delete(Customer entity) {
		if (entity == null)
			return entity;

		// DELETE COMPLETE RELATIONS OF CUSTOMER WITH ALL TABLES
		Map<String, Object> mapParameters = new HashMap<>();
		mapParameters.put("customerId", entity.getId());
		mapParameters.put("userId", entity.getUser().getId());

		this.namedJdbcTemplate.update(DELETE_ACCOUNT_TABLE_WHERE_CUSTOMER_ID, mapParameters);
		this.namedJdbcTemplate.update(DELETE_USER_WHERE_USER_ID, mapParameters);
		this.namedJdbcTemplate.update(DELETE_CUSTOMER_WHERE_CUSTOMER_ID, mapParameters);

		return entity;
	}

	@Override
	public List<Customer> findAll() {

		final List<Customer> customerList = new ArrayList<>();

		// FIND COMPLETE ALL CUSTOMER (WITH USER)
		this.namedJdbcTemplate.query(SELECT_ALL_CUSTOMER_USER, new RowCallbackHandler() {

			private CustomerRowMapper customerRowMapper = new CustomerRowMapper();

			private int i = 0;

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				customerList.add(customerRowMapper.mapRow(rs, i++));
			}
		});

		return customerList;
	}

}

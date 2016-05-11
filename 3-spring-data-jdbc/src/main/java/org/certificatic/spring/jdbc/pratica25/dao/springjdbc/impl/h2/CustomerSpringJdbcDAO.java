package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.impl.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.certificatic.spring.jdbc.pratica25.dao.api.ICustomerDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.parameterholder.CustomerBeanSqlParameterHolder;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.parameterholder.CustomerUserBeanSqlParameterHolder;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.parameterholder.UserBeanSqlParameterHolder;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Customer;
import org.certificatic.spring.jdbc.pratica25.domain.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Profile({ "h2-in-memory", "h2-local" })
public class CustomerSpringJdbcDAO extends GenericSpringJdbcDAO<Customer, Long> implements ICustomerDAO {

	@Override
	public void insert(Customer entity) {

		// INSERT CUSTOMER
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO SPRING_DATA_CUSTOMER_TBL VALUES (");
		sb.append("null, :name, :last_name").append(")");

		MapSqlParameterSource paramParameters = new MapSqlParameterSource();
		paramParameters.addValue("name", entity.getName());
		paramParameters.addValue("last_name", entity.getLastName());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.namedJdbcTemplate.update(sb.toString(), paramParameters, keyHolder);

		entity.setId(keyHolder.getKey().longValue());

		// INSERT USER
		sb.delete(0, sb.length());
		sb.append("INSERT INTO SPRING_DATA_USER_TBL VALUES (");
		sb.append("null, :fkCustomerId, :username, :password").append(")");

		paramParameters = new MapSqlParameterSource();
		paramParameters.addValue("fkCustomerId", entity.getUser().getCustomer().getId());
		paramParameters.addValue("username", entity.getUser().getUsername());
		paramParameters.addValue("password", entity.getUser().getPassword());

		keyHolder = new GeneratedKeyHolder();

		this.namedJdbcTemplate.update(sb.toString(), paramParameters, keyHolder);

		entity.getUser().setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Customer entity) {

		// UPDATE CUSTOMER
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE SPRING_DATA_CUSTOMER_TBL SET ");
		sb.append("NAME = :name, LAST_NAME = :last_name ");
		sb.append("WHERE CUSTOMER_ID = :customer_id");

		this.namedJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(new CustomerBeanSqlParameterHolder(entity)));

		// UPDATE USER
		sb.delete(0, sb.length());
		sb.append("UPDATE SPRING_DATA_USER_TBL SET ");
		sb.append("USERNAME = :username, PASSWORD = :password ");
		sb.append("WHERE USER_ID = :user_id");

		this.namedJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(new UserBeanSqlParameterHolder(entity.getUser())));

	}

	@Override
	public Customer findById(Long id) {
		Customer c = null;

		// FIND CUSTOMER BY ID
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM SPRING_DATA_CUSTOMER_TBL WHERE CUSTOMER_ID = :customerId");

		MapSqlParameterSource paramParameters = new MapSqlParameterSource();
		paramParameters.addValue("customerId", id);

		try {
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

			c = this.namedJdbcTemplate.queryForObject(sb.toString(), paramParameters, new RowMapper<Customer>() {
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
		sb.delete(0, sb.length());
		sb = new StringBuilder();
		sb.append("SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = :customerId");

		paramParameters = new MapSqlParameterSource();
		paramParameters.addValue("customerId", c.getId());

		c.setUser(
				this.namedJdbcTemplate.queryForObject(sb.toString(), paramParameters, (ResultSet rs, int rowNum) -> {
					User user = new User();
					user.setId(rs.getLong("USER_ID"));
					user.setUsername(rs.getString("USERNAME"));
					user.setPassword(rs.getString("PASSWORD"));
					System.out.println(user);
					return user;

				}));

		c.getUser().setCustomer(c);

		return c;
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
		final String DELETE_ACCOUNT_TABLE = "DELETE FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = :customerId";
		final String DELETE_USER_TABLE = "DELETE FROM SPRING_DATA_USER_TBL WHERE USER_ID = :userId";
		final String DELETE_CUSTOMER_TABLE = "DELETE FROM SPRING_DATA_CUSTOMER_TBL WHERE CUSTOMER_ID = :customerId";

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", entity.getId());
		this.namedJdbcTemplate.update(DELETE_ACCOUNT_TABLE, paramMap);

		paramMap = new HashMap<>();
		paramMap.put("userId", entity.getUser().getId());
		this.namedJdbcTemplate.update(DELETE_USER_TABLE, paramMap);

		paramMap = new HashMap<>();
		paramMap.put("customerId", entity.getId());
		this.namedJdbcTemplate.update(DELETE_CUSTOMER_TABLE, paramMap);

		return entity;
	}

	@Override
	public List<Customer> findAll() {

		List<Customer> customerList = null;

		// FIND COMPLETE ALL CUSTOMER (WITH USER)
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ")
				.append("SPRING_DATA_CUSTOMER_TBL INNER JOIN SPRING_DATA_USER_TBL ON CUSTOMER_ID=FK_CUSTOMER_ID");

		List<CustomerUserBeanSqlParameterHolder> customerUser = this.jdbcTemplate.query(sb.toString(),
				new BeanPropertyRowMapper<CustomerUserBeanSqlParameterHolder>(
						CustomerUserBeanSqlParameterHolder.class));

		customerList = customerUser.stream().map(cu -> {

			Customer c = Customer.builder().id(cu.getCustomer_id()).name(cu.getName())
					.lastName(cu.getLast_name()).build();

			User u = User.builder().id(cu.getUser_id()).username(cu.getUsername())
					.password(cu.getPassword()).build();

			u.setCustomer(c);
			c.setUser(u);

			return c;

		}).collect(Collectors.toList());

		return customerList;
	}

}

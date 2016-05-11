package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.certificatic.spring.jdbc.pratica25.dao.api.IUserDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.parameterholder.CustomerUserBeanSqlParameterHolder;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowcallbackhandler.AccountXmlRowCallbackHandler;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowmapper.AccountRowMapper;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Account;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Customer;
import org.certificatic.spring.jdbc.pratica25.domain.entities.User;
import org.certificatic.spring.jdbc.pratica25.domain.vo.CustomDate;
import org.certificatic.spring.oxm.util.XMLConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Profile({ "h2-in-memory", "h2-local", "mysql" })
public class UserSpringJdbcDAO extends GenericSpringJdbcDAO<User, Long> implements IUserDAO {

	@Autowired
	private XMLConverter xmlConverter;

	@Override
	public void insert(User entity) {

		// INSERT CUSTOMER
		final String INSERT_CUSTOMER;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO SPRING_DATA_CUSTOMER_TBL VALUES (");
		sb.append("null, ?, ?").append(")");

		KeyHolder keyHolder = new GeneratedKeyHolder();

		INSERT_CUSTOMER = sb.toString();

		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER,
						new String[] { "CUSTOMER_ID", "NAME", "LAST_NAME" });
				ps.setString(1, entity.getCustomer().getName());
				ps.setString(2, entity.getCustomer().getLastName());
				return ps;
			}
		}, keyHolder);

		entity.getCustomer().setId(keyHolder.getKey().longValue());

		// INSERT USER
		final String INSERT_USER;

		sb.delete(0, sb.length());
		sb = new StringBuilder();
		sb.append("INSERT INTO SPRING_DATA_USER_TBL VALUES (");
		sb.append("null, ?, ?, ?").append(")");

		keyHolder = new GeneratedKeyHolder();

		INSERT_USER = sb.toString();

		this.jdbcTemplate.update((Connection connection) -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_USER,
					new String[] { "USER_ID", "FK_CUSTOMER_ID", "USERNAME", "PASSWORD" });
			ps.setLong(1, entity.getCustomer().getId());
			ps.setString(2, entity.getUsername());
			ps.setString(3, entity.getPassword());
			return ps;
		}, keyHolder);

		entity.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(User entity) {
		// UPDATE CUSTOMER
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE SPRING_DATA_CUSTOMER_TBL SET ");
		sb.append("NAME = ?, LAST_NAME = ? ");
		sb.append("WHERE CUSTOMER_ID = ?");

		/*this.jdbcTemplate.update(sb.toString(), entity.getCustomer().getName(), entity.getCustomer().getLastName(),
				entity.getCustomer().getId());*/

		this.jdbcTemplate.update(sb.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCustomer().getName());
				ps.setString(2, entity.getCustomer().getLastName());
				ps.setLong(3, entity.getCustomer().getId());
			}
		});

		// UPDATE USER
		sb.delete(0, sb.length());
		sb.append("UPDATE SPRING_DATA_USER_TBL SET ");
		sb.append("USERNAME = ?, PASSWORD = ? ");
		sb.append("WHERE USER_ID = ?");

		this.jdbcTemplate.update(sb.toString(), entity.getUsername(), entity.getPassword(), entity.getId());
	}

	@Override
	@SneakyThrows
	public User findById(Long id) {
		User u = null;

		// FIND COMPLETE USER (WITH CUSTOMER) BY ID
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ")
				.append("SPRING_DATA_CUSTOMER_TBL INNER JOIN SPRING_DATA_USER_TBL ON CUSTOMER_ID=FK_CUSTOMER_ID ")
				.append("WHERE CUSTOMER_ID = ?");

		try {
			log.info("begin testing");

			String username = this.jdbcTemplate
					.queryForObject("SELECT USERNAME FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = ?", String.class,
							id);

			log.info("username: {}", username);

			u = this.jdbcTemplate
					.queryForObject("SELECT * FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = ?",
							new RowMapper<User>() {
								@Override
								public User mapRow(ResultSet rs, int rowNum) throws SQLException {
									User usr = new User();
									usr.setId(rs.getLong("USER_ID"));
									usr.setUsername(rs.getString("USERNAME"));
									usr.setPassword(rs.getString("PASSWORD"));
									return usr;
								}
							}, id);
			log.info("user: {}", u);

			Map<String, Object> mapResult = this.jdbcTemplate
					.queryForMap("SELECT USERNAME, PASSWORD FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID = ?",
							new Object[] { id });

			log.info("mapResult: {}", mapResult);

			List<String> usernames = this.jdbcTemplate
					.queryForList("SELECT USERNAME FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > ?",
							String.class, new Object[] { 0 });

			log.info("usernames: {}", usernames);

			List<Map<String, Object>> usernames2 = this.jdbcTemplate
					.queryForList("SELECT USERNAME FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > ?",
							new Object[] { 0 });

			log.info("usernames2: {}", usernames2);

			List<Map<String, Object>> usernameAndPassword = this.jdbcTemplate
					.queryForList("SELECT USERNAME, PASSWORD FROM SPRING_DATA_USER_TBL WHERE FK_CUSTOMER_ID > ?",
							new Object[] { 0 });

			log.info("usernameAndPassword: {}", usernameAndPassword);

			List<Account> accounts = this.jdbcTemplate
					.query("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?", new AccountRowMapper(),
							1);

			log.info("accounts: {}", accounts);

			Map<String, Object> map = new HashMap<>();
			map.put("fk_customer_id", 1);

			accounts = this.namedJdbcTemplate
					.query("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = :fk_customer_id", map,
							new AccountRowMapper());

			log.info("lola accounts: {}", accounts);

			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("fk_customer_id", 1);

			accounts = this.namedJdbcTemplate
					.query("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = :fk_customer_id", paramSource,
							new AccountRowMapper());

			log.info("lola2 accounts: {}", accounts);

			List<Account> accounts2 = this.jdbcTemplate
					.query("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?",
							new ResultSetExtractor<List<Account>>() {

								@Override
								public List<Account> extractData(ResultSet rs)
										throws SQLException, DataAccessException {
									List<Account> la = new ArrayList<>();

									while (rs.next()) {
										Account account = new Account();

										Customer customer = new Customer();
										customer.setId(rs.getLong("FK_CUSTOMER_ID"));

										account.setCustomer(customer);

										account.setId(rs.getLong("ACCOUNT_ID"));
										account.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
										account.setCreatedDate(new CustomDate(rs.getDate("CREATED_DATE").getTime()));
										account.setBalance(rs.getBigDecimal("BALANCE"));

										la.add(account);
									}

									return la;
								}
							}, 1);

			log.info("accounts2: {}", accounts2);

			AccountXmlRowCallbackHandler xmlAccountCallbackHandler = new AccountXmlRowCallbackHandler();

			this.jdbcTemplate.query("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?",
					xmlAccountCallbackHandler, 1);

			xmlConverter.convertFromObjectToXML(xmlAccountCallbackHandler.getAccountList(), "account.xml");

			log.info("account.xml: {}", xmlConverter.getXMLAsString("file:account.xml"));

			///////////////////////////////////////

			u = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					Customer customer = new Customer();
					user.setCustomer(customer);
					customer.setUser(user);

					user.setId(rs.getLong("USER_ID"));
					user.setUsername(rs.getString("USERNAME"));
					user.setPassword(rs.getString("PASSWORD"));

					customer.setId(rs.getLong("CUSTOMER_ID"));
					customer.setName(rs.getString("NAME"));
					customer.setLastName(rs.getString("LAST_NAME"));
					return user;
				}
			}, id);

		} catch (EmptyResultDataAccessException ex) {
			// Cuando se usa queryForObject se espera al menos 1 resultado.
			return null;
		}

		return u;
	}

	@Override
	public User delete(Long id) {
		User user = this.findById(id);
		return this.delete(user);
	}

	@Override
	public User delete(User entity) {
		if (entity == null)
			return entity;

		// DELETE COMPLETE RELATIONS OF USER WITH ALL TABLES
		final String DELETE_ACCOUNT_TABLE = "DELETE FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?";
		final String DELETE_USER_TABLE = "DELETE FROM SPRING_DATA_USER_TBL WHERE USER_ID = ?";
		final String DELETE_CUSTOMER_TABLE = "DELETE FROM SPRING_DATA_CUSTOMER_TBL WHERE CUSTOMER_ID = ?";

		this.jdbcTemplate.update(DELETE_ACCOUNT_TABLE, entity.getCustomer().getId());

		this.jdbcTemplate.update(DELETE_USER_TABLE, entity.getId());

		this.jdbcTemplate.update(DELETE_CUSTOMER_TABLE, entity.getCustomer().getId());

		return entity;
	}

	@Override
	public List<User> findAll() {

		List<User> userList = null;

		// FIND COMPLETE ALL USER (WITH CUSTOMER)
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ")
				.append("SPRING_DATA_CUSTOMER_TBL INNER JOIN SPRING_DATA_USER_TBL ON CUSTOMER_ID=FK_CUSTOMER_ID");

		List<CustomerUserBeanSqlParameterHolder> customerUser = this.jdbcTemplate.query(sb.toString(),
				new BeanPropertyRowMapper<CustomerUserBeanSqlParameterHolder>(
						CustomerUserBeanSqlParameterHolder.class));

		userList = customerUser.stream().map(cu -> {

			Customer c = Customer.builder().id(cu.getCustomer_id()).name(cu.getName())
					.lastName(cu.getLast_name()).build();

			User u = User.builder().id(cu.getUser_id()).username(cu.getUsername())
					.password(cu.getPassword()).build();

			u.setCustomer(c);
			c.setUser(u);

			return u;

		}).collect(Collectors.toList());

		return userList;
	}

}

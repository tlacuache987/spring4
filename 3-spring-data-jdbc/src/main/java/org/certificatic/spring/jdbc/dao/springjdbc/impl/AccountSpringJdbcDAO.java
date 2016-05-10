package org.certificatic.spring.jdbc.dao.springjdbc.impl;

import java.util.List;

import org.certificatic.spring.jdbc.dao.api.IAccountDAO;
import org.certificatic.spring.jdbc.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.dao.springjdbc.parameterholder.AccountBeanSqlParameterHolder;
import org.certificatic.spring.jdbc.dao.springjdbc.rowmapper.AccountRowMapper;
import org.certificatic.spring.jdbc.domain.entities.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Profile({ "h2-in-memory", "h2-local", "mysql" })
public class AccountSpringJdbcDAO extends GenericSpringJdbcDAO<Account, Long> implements IAccountDAO {

	@Override
	public List<Account> findByCustomerId(Long id) {

		// FIND ACCOUNT BY CUSTOMER ID
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?");

		return this.jdbcTemplate.query(sb.toString(), new AccountRowMapper(), id);
	}

	@Override
	public void insert(Account entity) {
		// INSERT Account
		final String INSERT_ACCOUNT;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO SPRING_DATA_ACCOUNT_TBL VALUES (");
		sb.append("null, :fk_customer_id, :account_number, :created_date, :balance").append(")");

		KeyHolder keyHolder = new GeneratedKeyHolder();

		INSERT_ACCOUNT = sb.toString();

		this.namedJdbcTemplate.update(INSERT_ACCOUNT,
				new BeanPropertySqlParameterSource(new AccountBeanSqlParameterHolder(entity)), keyHolder);

		entity.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Account entity) {
		// UPDATE ACCOUNT
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE SPRING_DATA_ACCOUNT_TBL SET ");
		sb.append("ACCOUNT_NUMBER = ?, CREATED_DATE = ?, BALANCE = ?");
		sb.append("WHERE CUSTOMER_ID = ?");

		this.jdbcTemplate.update(sb.toString(), entity.getAccountNumber(), entity.getCreatedDate(),
				entity.getBalance(), entity.getCustomer().getId());
	}

	@Override
	public Account findById(Long id) {
		Account account = null;

		// FIND ACCOUNT BY ID
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE ACCOUNT_ID = ?");

		try {
			account = this.jdbcTemplate.queryForObject(sb.toString(), new AccountRowMapper(), id);

		} catch (EmptyResultDataAccessException ex) {
			// Cuando se usa queryForObject se espera al menos 1 resultado.
			return null;
		}

		return account;
	}

	@Override
	public Account delete(Long id) {
		Account account = this.findById(id);
		return this.delete(account);
	}

	@Override
	public Account delete(Account entity) {
		if (entity == null)
			return entity;

		// DELETE ACCOUNT
		final String DELETE_ACCOUNT_TABLE = "DELETE FROM SPRING_DATA_ACCOUNT_TBL WHERE ACCOUNT_ID = ?";

		this.jdbcTemplate.update(DELETE_ACCOUNT_TABLE, entity.getId());

		return entity;
	}

	@Override
	public List<Account> findAll() {
		List<Account> userList = null;

		// FIND ALL Account
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM SPRING_DATA_ACCOUNT_TBL");

		userList = this.jdbcTemplate.query(sb.toString(), new AccountRowMapper());

		return userList;
	}

}

package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.impl;

import java.util.List;

import org.certificatic.spring.jdbc.pratica25.dao.api.IAccountDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowmapper.AccountRowMapper;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Profile({ "h2-in-memory", "h2-local", "mysql" })
public class AccountSpringJdbcDAO extends GenericSpringJdbcDAO<Account, Long> implements IAccountDAO {

	private static final String SELECT_ALL_ACCOUNT_WHERE_CUSTOMER_ID = "SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?";
	private static final String SELECT_ALL_ACCOUNT = "SELECT * FROM SPRING_DATA_ACCOUNT_TBL";
	private static final String SELECT_ACCOUNT = "SELECT * FROM SPRING_DATA_ACCOUNT_TBL WHERE ACCOUNT_ID = ?";

	private static final String INSERT_ACCOUNT = "INSERT INTO SPRING_DATA_ACCOUNT_TBL VALUES (null, :fkCustomerId, :accountNumber, :createdDate, :balance)";

	private static final String UPDATE_ACCOUNT_WHERE_ACCOUNT_ID = "UPDATE SPRING_DATA_ACCOUNT_TBL SET ACCOUNT_NUMBER = ?, CREATED_DATE = ?, BALANCE = ? WHERE ACCOUNT_ID = ?";

	private static final String DELETE_ACCOUNT = "DELETE FROM SPRING_DATA_ACCOUNT_TBL WHERE ACCOUNT_ID = ?";

	@Override
	public List<Account> findByCustomerId(Long id) {

		// FIND ACCOUNT BY CUSTOMER ID
		return this.jdbcTemplate.query(SELECT_ALL_ACCOUNT_WHERE_CUSTOMER_ID, new AccountRowMapper(), id);
	}

	@Override
	public void insert(Account entity) {
		// INSERT Account
		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("fkCustomerId", entity.getCustomer().getId());
		parameterSource.addValue("accountNumber", entity.getAccountNumber());
		parameterSource.addValue("createdDate", entity.getCreatedDate());
		parameterSource.addValue("balance", entity.getBalance());

		this.namedJdbcTemplate.update(INSERT_ACCOUNT, parameterSource, keyHolder);

		entity.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Account entity) {
		// UPDATE ACCOUNT
		this.jdbcTemplate.update(UPDATE_ACCOUNT_WHERE_ACCOUNT_ID, entity.getAccountNumber(), entity.getCreatedDate(),
				entity.getBalance(), entity.getId());
	}

	@Override
	public Account findById(Long id) {
		Account account = null;

		// FIND ACCOUNT BY ID
		try {
			account = this.jdbcTemplate.queryForObject(SELECT_ACCOUNT, new AccountRowMapper(), id);

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
		this.jdbcTemplate.update(DELETE_ACCOUNT, entity.getId());

		return entity;
	}

	@Override
	public List<Account> findAll() {
		List<Account> userList = null;

		// FIND ALL Account
		userList = this.jdbcTemplate.query(SELECT_ALL_ACCOUNT, new AccountRowMapper());

		return userList;
	}

}

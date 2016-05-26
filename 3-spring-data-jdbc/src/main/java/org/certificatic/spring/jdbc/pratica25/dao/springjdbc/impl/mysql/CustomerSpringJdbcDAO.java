package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.impl.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.certificatic.spring.jdbc.pratica25.dao.api.ICustomerDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.mapper.UserEntity;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.object.CustomerMappingSqlQuery;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.object.CustomerSqlUpdate;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.object.UserSqlUpdate;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowmapper.CustomerRowMapper;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Customer;
import org.certificatic.spring.jdbc.pratica25.domain.entities.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mysql")
public class CustomerSpringJdbcDAO extends GenericSpringJdbcDAO<Customer, Long>
		implements ICustomerDAO, InitializingBean {

	private SimpleJdbcCall readCustomerProcedure;

	private SimpleJdbcInsert insertCustomer;
	private SimpleJdbcInsert insertUser;

	@SuppressWarnings("unused")
	private CustomerMappingSqlQuery customerSqlQuery;

	private CustomerSqlUpdate customerSqlUpdate;
	private UserSqlUpdate userSqlUpdate;

	private static final String SELECT_ALL_CUSTOMER_USER = "SELECT * FROM SPRING_DATA_CUSTOMER_TBL, SPRING_DATA_USER_TBL WHERE CUSTOMER_ID = FK_CUSTOMER_ID";

	@Override
	public void afterPropertiesSet() throws Exception {
		this.readCustomerProcedure = new SimpleJdbcCall(this.getJdbcTemplate().getDataSource())
				.withProcedureName("read_customer_user");

		this.insertCustomer = new SimpleJdbcInsert(this.getJdbcTemplate().getDataSource())
				.withTableName("SPRING_DATA_CUSTOMER_TBL").usingGeneratedKeyColumns("CUSTOMER_ID");
		this.insertUser = new SimpleJdbcInsert(this.getJdbcTemplate().getDataSource())
				.withTableName("SPRING_DATA_USER_TBL").usingGeneratedKeyColumns("USER_ID");

		this.customerSqlQuery = new CustomerMappingSqlQuery(this.getJdbcTemplate().getDataSource());
		this.customerSqlUpdate = new CustomerSqlUpdate(this.getJdbcTemplate().getDataSource());
		this.userSqlUpdate = new UserSqlUpdate(this.getJdbcTemplate().getDataSource());
	}

	@Override
	public void insert(Customer entity) {

		// INSERT CUSTOMER
		KeyHolder keyHolder = this.insertCustomer.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(entity));

		entity.setId(keyHolder.getKey().longValue());

		// INSERT USER
		UserEntity userEntity = UserEntity.map(entity.getUser());

		keyHolder = this.insertUser.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(userEntity));

		entity.getUser().setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Customer entity) {

		// UPDATE CUSTOMER
		this.customerSqlUpdate.execute(entity.getId(), entity.getName(), entity.getLastName());

		// UPDATE USER
		this.userSqlUpdate.execute(entity.getUser().getId(), entity.getUser().getUsername(),
				entity.getUser().getPassword());
	}

	@Override
	public Customer findById(Long id) {
		/*Customer c = null;
		
		// FIND CUSTOMER BY ID
		try {
			c = this.customerSqlQuery.findObject(id);
		
		} catch (EmptyResultDataAccessException ex) {
			// Cuando se usa queryForObject se espera al menos 1 resultado.
			return null;
		}*/

		/*StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM SPRING_DATA_CUSTOMER_TBL WHERE CUSTOMER_ID = :customerId");
		
		MapSqlParameterSource paramParameters = new MapSqlParameterSource();
		paramParameters.addValue("customerId", id);
		
		try {
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
		}*/

		// FIND USER OF CUSTOMER BY CUSTOMER_ID
		/*sb.delete(0, sb.length());
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
		
				}));*/

		// FIND USER OF CUSTOMER BY CUSTOMER_ID
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("in_customerId", id);

		Map<String, Object> out = readCustomerProcedure.execute(parameterSource);

		if ((Integer) out.get("#update-count-1") == 0)
			return null;

		User u = new User();
		Customer c = new Customer();

		u.setId(new Long((Integer) out.get("out_user_id")));
		u.setUsername((String) out.get("out_username"));
		u.setPassword((String) out.get("out_password"));

		c.setId(new Long((Integer) out.get("out_customer_id")));
		c.setName((String) out.get("out_name"));
		c.setLastName((String) out.get("out_last_name"));
		c.setUser(u);
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
		paramMap.put("userId", entity.getUser().getId());

		this.namedJdbcTemplate.update(DELETE_ACCOUNT_TABLE, paramMap);
		this.namedJdbcTemplate.update(DELETE_USER_TABLE, paramMap);
		this.namedJdbcTemplate.update(DELETE_CUSTOMER_TABLE, paramMap);

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

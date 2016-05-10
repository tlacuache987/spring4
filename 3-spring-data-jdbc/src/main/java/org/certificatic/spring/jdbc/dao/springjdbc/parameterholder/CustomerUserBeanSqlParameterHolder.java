package org.certificatic.spring.jdbc.dao.springjdbc.parameterholder;

import org.certificatic.spring.jdbc.domain.entities.Customer;
import org.certificatic.spring.jdbc.domain.entities.User;

import lombok.Data;

@Data
public class CustomerUserBeanSqlParameterHolder {

	private Long customer_id;

	private Long user_id;

	private String name;

	private String last_name;

	private String username;

	private String password;

	public CustomerUserBeanSqlParameterHolder() {

	}

	public CustomerUserBeanSqlParameterHolder(Customer customer) {
		this.customer_id = customer.getId();
		this.user_id = customer.getUser().getId();
		this.name = customer.getName();
		this.last_name = customer.getLastName();
		this.username = customer.getUser().getUsername();
		this.password = customer.getUser().getPassword();
	}

	public CustomerUserBeanSqlParameterHolder(User user) {
		this.customer_id = user.getCustomer().getId();
		this.user_id = user.getId();
		this.name = user.getCustomer().getName();
		this.last_name = user.getCustomer().getLastName();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

}
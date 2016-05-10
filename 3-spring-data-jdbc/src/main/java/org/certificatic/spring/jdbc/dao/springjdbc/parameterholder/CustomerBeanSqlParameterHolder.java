package org.certificatic.spring.jdbc.dao.springjdbc.parameterholder;

import org.certificatic.spring.jdbc.domain.entities.Customer;

import lombok.Data;

@Data
public class CustomerBeanSqlParameterHolder {

	private Long customer_id;

	private String name;

	private String last_name;

	public CustomerBeanSqlParameterHolder(Customer customer) {
		this.customer_id = customer.getId();
		this.name = customer.getName();
		this.last_name = customer.getLastName();
	}

}
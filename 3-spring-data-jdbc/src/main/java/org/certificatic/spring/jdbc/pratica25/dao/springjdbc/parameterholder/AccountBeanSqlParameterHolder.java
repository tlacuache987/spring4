package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.parameterholder;

import java.math.BigDecimal;

import org.certificatic.spring.jdbc.pratica25.domain.entities.Account;
import org.certificatic.spring.jdbc.pratica25.domain.vo.CustomDate;

import lombok.Data;

@Data
public class AccountBeanSqlParameterHolder {

	private Long account_id;

	private Long fk_customer_id;

	private String account_number;

	private CustomDate created_date;

	private BigDecimal balance;

	public AccountBeanSqlParameterHolder(Account account) {
		this.account_id = account.getId();
		this.fk_customer_id = account.getCustomer().getId();
		this.account_number = account.getAccountNumber();
		this.created_date = account.getCreatedDate();
		this.balance = account.getBalance();
	}
}
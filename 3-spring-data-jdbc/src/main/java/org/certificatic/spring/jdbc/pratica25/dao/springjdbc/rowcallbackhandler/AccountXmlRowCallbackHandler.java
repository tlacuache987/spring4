package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowcallbackhandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowmapper.AccountRowMapper;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Account;
import org.springframework.jdbc.core.RowCallbackHandler;

import lombok.Getter;

public class AccountXmlRowCallbackHandler implements RowCallbackHandler {

	private @Getter List<Account> accountList = new ArrayList<>();

	private AccountRowMapper accountRowMapper = new AccountRowMapper();

	private int i = 0;

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		Account account = accountRowMapper.mapRow(rs, i++);
		accountList.add(account);
	}

	public void reset() {
		this.i = 0;
		this.accountList = new ArrayList<>();
	}
}

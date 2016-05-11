package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.parameterholder;

import org.certificatic.spring.jdbc.pratica25.domain.entities.User;

import lombok.Data;

@Data
public class UserBeanSqlParameterHolder {

	private Long user_id;

	private String username;

	private String password;

	public UserBeanSqlParameterHolder(User user) {
		this.user_id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
}
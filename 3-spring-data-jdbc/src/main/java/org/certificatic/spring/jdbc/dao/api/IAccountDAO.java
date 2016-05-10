package org.certificatic.spring.jdbc.dao.api;

import java.util.List;

import org.certificatic.spring.jdbc.dao.IGenericDAO;
import org.certificatic.spring.jdbc.domain.entities.Account;

public interface IAccountDAO extends IGenericDAO<Account, Long> {

	List<Account> findByCustomerId(Long id);
}

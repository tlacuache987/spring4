package org.certificatic.spring.soba.service.account.api;

import java.math.BigDecimal;
import java.util.List;

import org.certificatic.spring.soba.domain.Account;

public interface IAccountService {

	Account getByAccountId(Long id);

	List<Account> getByCustomerId(Long id);

	String transfer(Account origin, Account destination, BigDecimal amount, String description);

}

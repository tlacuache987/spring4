package org.certificatic.spring.mvcsecurity.practica32.service.api.impl;

import org.certificatic.spring.mvcsecurity.practica32.service.api.IDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("admin-data-service")
public class AdminDataService implements IDataService {

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getData() {
		return "admin DATA ....";
	}

}

package org.certificatic.spring.mvcsecurity.practica32.service.api.impl;

import org.certificatic.spring.mvcsecurity.practica32.service.api.IDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("root-data-service")
public class RootDataService implements IDataService {

	@Override
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public String getData() {
		return "root DATA ....";
	}

}

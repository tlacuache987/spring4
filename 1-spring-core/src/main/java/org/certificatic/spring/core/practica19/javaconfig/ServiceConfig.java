package org.certificatic.spring.core.practica19.javaconfig;

import org.certificatic.spring.core.practica19.javaconfig.bean.DummyRepository;
import org.certificatic.spring.core.practica19.javaconfig.bean.DummyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

	@Bean
	public DummyService dummyService(@Qualifier("dummyRepository") DummyRepository dummyRespository) {
		return new DummyService(dummyRespository);
	}

	@Bean
	public DummyService dummyService2(@Qualifier("dummyRepository2") DummyRepository dummyRespository) {
		return new DummyService(dummyRespository);
	}
}

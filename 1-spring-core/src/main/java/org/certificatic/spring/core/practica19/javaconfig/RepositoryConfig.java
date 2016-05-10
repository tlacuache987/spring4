package org.certificatic.spring.core.practica19.javaconfig;

import org.certificatic.spring.core.practica19.javaconfig.bean.DummyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath:/spring/practica19/datasource-application-context.xml" })
public class RepositoryConfig {

	@Bean
	public DummyRepository dummyRepository(String connectionString) {
		return new DummyRepository(connectionString);
	}

	@Bean
	public DummyRepository dummyRepository2(String connectionString) {
		return new DummyRepository(connectionString);
	}
}

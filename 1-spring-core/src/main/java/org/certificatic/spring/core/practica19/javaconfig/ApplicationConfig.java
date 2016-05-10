package org.certificatic.spring.core.practica19.javaconfig;

import org.certificatic.spring.core.practica19.javaconfig.bean.api.IQuadraticEquationService;
import org.certificatic.spring.core.practica19.javaconfig.bean.api.impl.QuadraticEquationServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = { "org.certificatic.spring.core.practica19.javaconfig" })
@Import({ RepositoryConfig.class, ServiceConfig.class })
public class ApplicationConfig {

	@Bean
	public IQuadraticEquationService quadraticService() {
		return new QuadraticEquationServiceImpl();
	}

	@Bean(initMethod = "init", destroyMethod = "destroy")
	@Qualifier("quadraticEquationServiceBean")
	public IQuadraticEquationService quadraticService2() {
		return new QuadraticEquationServiceImpl();
	}

	@Bean
	@Scope("prototype")
	public IQuadraticEquationService quadraticService3() {
		return new QuadraticEquationServiceImpl();
	}

}

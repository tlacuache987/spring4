package org.certificatic.spring.core.practica5.test.scopes;

import org.certificatic.spring.core.practica5.scopes.bean.Persona;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomScopeTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		applicationContext = new ClassPathXmlApplicationContext("spring/practica5/scopes-application-context.xml");
	}

	@Test
	public void customScopeTest() {

		log.info("customScopeTest -------------------");

		for (int i = 1; i <= 10; i++) {
			Persona persona = applicationContext.getBean("personaCustomscopeBean",
					Persona.class);

			Assert.assertNotNull(persona);
			log.info("persona[i]: {} {} {}", persona, persona.hashCode(),
					System.identityHashCode(persona));
		}
	}

}

package org.certificatic.spring.core.practica5.test.scopes;

import org.certificatic.spring.core.practica5.scopes.bean.Persona;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScopesTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		applicationContext = new ClassPathXmlApplicationContext("spring/practica5/scopes-application-context.xml");
	}

	@Test
	public void scopeSingletonTest() {

		log.info("scopeSingletonTest -------------------");

		for (int i = 0; i < 3; i++) {
			Persona persona = applicationContext.getBean("personaSingletonBean",
					Persona.class);

			Assert.assertNotNull(persona);
			log.info("persona: {} {} {}", persona, persona.hashCode(),
					System.identityHashCode(persona));
		}
	}

	@Test
	public void scopeSingletonTest2() {

		log.info("scopeSingletonTest2 -------------------");

		Persona persona = applicationContext.getBean("personaSingletonBean",
				Persona.class);

		Persona persona2 = applicationContext.getBean("personaSingletonBean",
				Persona.class);

		Assert.assertNotNull(persona);
		Assert.assertNotNull(persona2);
		Assert.assertEquals(persona, persona2);
		Assert.assertSame(persona, persona2);

		log.info("persona: {} {} {}", persona, persona.hashCode(),
				System.identityHashCode(persona));

		log.info("persona2: {} {} {}", persona2, persona2.hashCode(),
				System.identityHashCode(persona2));
	}

	@Test
	public void scopePrototypeTest() {

		log.info("scopePrototypeTest -------------------");
		for (int i = 0; i < 3; i++) {
			Persona persona = applicationContext.getBean("personaPrototypeBean",
					Persona.class);

			Assert.assertNotNull(persona);
			log.info("persona: {} {} {}", persona, persona.hashCode(),
					System.identityHashCode(persona));
		}
	}

	@Test
	public void scopePrototypeTest2() {

		log.info("scopePrototypeTest2 -------------------");

		Persona persona = applicationContext.getBean("personaPrototypeBean",
				Persona.class);

		Persona persona2 = applicationContext.getBean("personaPrototypeBean",
				Persona.class);

		Assert.assertNotNull(persona);
		Assert.assertNotNull(persona2);
		Assert.assertEquals(persona, persona2);
		Assert.assertNotSame(persona, persona2);

		log.info("persona: {} {} {}", persona, persona.hashCode(),
				System.identityHashCode(persona));

		log.info("persona2: {} {} {}", persona2, persona2.hashCode(),
				System.identityHashCode(persona2));
	}

}

package org.certificatic.spring.core.practica11.test.innerbeans;

import org.certificatic.spring.core.practica11.innerbeans.bean.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InnerBeansTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@Before
	public void beforeTest() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica11/inner-beans-application-context.xml");
	}

	@Test
	public void innerBeansTest() {

		log.info("innerBeansTest -------------------");

		Person person = applicationContext.getBean(Person.class);

		Assert.assertNotNull(person);

		log.info("person: {}", person);

		((AbstractApplicationContext) applicationContext).close();
	}

	@Test
	public void gettingStreetNameTest() {

		log.info("gettingStreetNameTest -------------------");

		String streetName = applicationContext.getBean("streetName", String.class);

		Assert.assertNotNull(streetName);
		Assert.assertEquals("Paseo de los Pirules", streetName);

		log.info("streetName: {}", streetName);

		((AbstractApplicationContext) applicationContext).close();
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void gettingNameTest() {

		log.info("gettingNameTest -------------------");

		@SuppressWarnings("unused")
		String name = applicationContext.getBean("nameBean", String.class);

		Assert.fail("Should have to fail at this line");

		((AbstractApplicationContext) applicationContext).close();
	}

}

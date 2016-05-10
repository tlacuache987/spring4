package org.certificatic.spring.core.practica8.test.factorymethod;

import org.certificatic.spring.core.practica8.factorymethod.bean.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryMethodTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@Before
	public void beforeClass() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica8/factory-method-application-context.xml");
	}

	@Test
	public void factoryMethodTest() {

		log.info("factoryMethodTest -------------------");

		Student student = applicationContext.getBean("student1", Student.class);

		Assert.assertNotNull(student);

		log.info("student: {}", student);

		((AbstractApplicationContext) applicationContext).close();
	}

	@Test
	public void factoryMethodTest2() {

		log.info("factoryMethodTest2 -------------------");

		Student student = applicationContext.getBean("student2", Student.class);

		Assert.assertNotNull(student);

		log.info("student: {}", student);

		((AbstractApplicationContext) applicationContext).close();
	}

}

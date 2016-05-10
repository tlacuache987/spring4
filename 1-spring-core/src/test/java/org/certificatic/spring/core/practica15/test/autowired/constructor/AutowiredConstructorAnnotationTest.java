package org.certificatic.spring.core.practica15.test.autowired.constructor;

import org.certificatic.spring.core.practica15.autowired.constructor.bean.Engineer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredConstructorAnnotationTest {

	@Test
	public void autowiredConstructorAnnotationTest() {

		log.info("autowiredConstructorAnnotationTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica15/annotations-autowired-constructor-application-context.xml");

		Engineer engineer = applicationContext.getBean(Engineer.class);

		Assert.assertNotNull(engineer);

		log.info("engineer: {}", engineer);

		((AbstractApplicationContext) applicationContext).close();
	}

}

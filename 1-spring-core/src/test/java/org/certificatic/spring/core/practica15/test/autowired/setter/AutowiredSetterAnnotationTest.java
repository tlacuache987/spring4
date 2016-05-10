package org.certificatic.spring.core.practica15.test.autowired.setter;

import org.certificatic.spring.core.practica15.autowired.setter.bean.Journalist;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredSetterAnnotationTest {

	@Test
	public void autowiredSetterAnnotationTest() {

		log.info("autowiredSetterAnnotationTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica15/annotations-autowired-setter-application-context.xml");

		Journalist jouralist = applicationContext.getBean(Journalist.class);

		Assert.assertNotNull(jouralist);

		log.info("jouralist: {}", jouralist);

		((AbstractApplicationContext) applicationContext).close();
	}

}

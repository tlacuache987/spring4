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

		String ctxFile = "spring/practica15/annotations-autowired-setter-application-context.xml";

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(ctxFile);

		Journalist jouralist = applicationContext.getBean(Journalist.class);

		Assert.assertNotNull(jouralist);

		Assert.assertNull(jouralist.getDni());

		Assert.assertNotNull(jouralist.getName());

		Assert.assertNotNull(jouralist.getAge());

		Assert.assertNotNull(jouralist.getNotebook());

		Assert.assertNotNull(jouralist.getPen());

		log.info("jouralist: {}", jouralist);

		((AbstractApplicationContext) applicationContext).close();
	}

}

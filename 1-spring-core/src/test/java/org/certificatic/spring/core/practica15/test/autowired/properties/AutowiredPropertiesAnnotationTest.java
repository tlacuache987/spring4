package org.certificatic.spring.core.practica15.test.autowired.properties;

import org.certificatic.spring.core.practica15.autowired.properties.bean.Reporter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredPropertiesAnnotationTest {

	@Test
	public void autowiredPropertiesAnnotationTest() {

		log.info("autowiredPropertiesAnnotationTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica15/annotations-autowired-property-application-context.xml");

		Reporter reporter = applicationContext.getBean(Reporter.class);

		Assert.assertNotNull(reporter);

		log.info("reporter: {}", reporter);

		((AbstractApplicationContext) applicationContext).close();
	}

}

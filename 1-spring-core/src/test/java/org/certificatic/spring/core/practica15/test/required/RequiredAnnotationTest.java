package org.certificatic.spring.core.practica15.test.required;

import org.certificatic.spring.core.practica15.required.bean.Cameraman;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequiredAnnotationTest {

	@Test
	public void requiredAnnotationTest() {

		log.info("requiredAnnotationTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica15/annotations-required-application-context.xml");

		Cameraman cameraman = applicationContext.getBean("okCameraman", Cameraman.class);

		Assert.assertNotNull(cameraman);

		log.info("cameraman: {}", cameraman);

		((AbstractApplicationContext) applicationContext).close();
	}

	@Test(expected = BeanCreationException.class)
	public void badRequiredAnnotationTest() {

		log.info("badRequiredAnnotationTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica15/annotations-required-application-context.xml");

		Cameraman cameraman = applicationContext.getBean("badCameraman", Cameraman.class);

		Assert.assertNull(cameraman);

		Assert.fail("Test must have fails");

		((AbstractApplicationContext) applicationContext).close();
	}

}

package org.certificatic.spring.core.practica15.test.autowired.methods;

import org.certificatic.spring.core.practica15.autowired.methods.bean.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredMethodsAnnotationTest {

	@Test
	public void autowiredMethodsAnnotationTest() {

		log.info("autowiredMethodsAnnotationTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica15/annotations-autowired-methods-application-context.xml");

		Student student = applicationContext.getBean(Student.class);

		Assert.assertNotNull(student);

		log.info("student: {}", student);

		((AbstractApplicationContext) applicationContext).close();
	}

}

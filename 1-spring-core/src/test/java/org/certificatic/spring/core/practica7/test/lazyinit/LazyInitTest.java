package org.certificatic.spring.core.practica7.test.lazyinit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LazyInitTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica7/lazy-initialization-application-context.xml");
	}

	@Test
	public void lazyInitTest() {

		log.info("lazyInitTest -------------------");

		/*Car car = applicationContext.getBean(Car.class);
		
		car = applicationContext.getBean(Car.class);
		
		car = applicationContext.getBean(Car.class);
		
		Assert.assertNotNull(car);
		
		log.info("car: {}", car);*/

		((AbstractApplicationContext) applicationContext).close();
	}

}

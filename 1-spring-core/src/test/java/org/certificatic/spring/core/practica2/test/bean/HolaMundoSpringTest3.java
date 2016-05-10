package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest3 {

	private static ApplicationContext factory;

	@BeforeClass
	public static void beforeClass() {
		factory = new ClassPathXmlApplicationContext("spring/practica2/beans.xml");
	}

	@Before
	public void beforeTests() {
		log.info("Esto se ejecutara antes de cada @Test");
	}

	@Test
	public void holaMundoSpringTest3() {
		
		log.info("holaMundoSpringTest3 -------------------");

		HolaMundo hola = (HolaMundo) factory.getBean("holaMundoBean");

		hola.setMensaje("Un mensaje");

		HolaMundo hola2 = (HolaMundo) factory.getBean("holaMundoBean");

		HolaMundo hola3 = (HolaMundo) factory.getBean("holaMundoBean");

		log.info("{}", hola);
		log.info("{}", hola2);
		log.info("{}", hola3);

		Assert.assertSame(hola, hola2);
		Assert.assertSame(hola2, hola3);
		Assert.assertSame(hola, hola3);

		((AbstractApplicationContext) factory).close();

	}

}

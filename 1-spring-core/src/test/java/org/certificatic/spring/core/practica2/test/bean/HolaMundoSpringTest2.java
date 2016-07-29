package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest2 {

	@Test
	public void holaMundoSpringTest2() {
		log.info("holaMundoSpringTest2 -------------------");

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/practica2/beans.xml");

		HolaMundo hola = (HolaMundo) context.getBean("holaMundoBean");

		hola.setMensaje("Un mensaje");

		HolaMundo hola2 = (HolaMundo) context.getBean("holaMundoBean");

		HolaMundo hola3 = (HolaMundo) context.getBean("holaMundoBean");

		log.info("{}", hola);
		log.info("{}", hola2);
		log.info("{}", hola3);

		Assert.assertSame(hola, hola2);
		Assert.assertSame(hola2, hola3);
		Assert.assertSame(hola, hola3);

		((AbstractApplicationContext) context).close();

	}

}

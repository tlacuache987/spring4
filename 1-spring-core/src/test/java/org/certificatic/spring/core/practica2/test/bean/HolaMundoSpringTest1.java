package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
public class HolaMundoSpringTest1 {

	@Test
	public void holaMundoSpringTest1() {
		log.info("holaMundoSpringTest1 -------------------");

		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring/practica2/beans.xml"));

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
	}

}

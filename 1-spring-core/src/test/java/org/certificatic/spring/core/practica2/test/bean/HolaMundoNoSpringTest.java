package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoNoSpringTest {

	@Test
	public void holaMundoNoSpringTest() {
		log.info("holaMundoNoSpringTest -------------------");
		
		HolaMundo hola = null;

		// por constructor
		// hola = new HolaMundo("hola =)");

		// por setter
		hola = new HolaMundo();
		hola.setMensaje("hola =");

		Assert.assertNotNull(hola);

		log.info("{}", hola);
	}

}

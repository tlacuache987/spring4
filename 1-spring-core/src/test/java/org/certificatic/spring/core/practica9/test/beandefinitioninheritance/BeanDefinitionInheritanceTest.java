package org.certificatic.spring.core.practica9.test.beandefinitioninheritance;

import org.certificatic.spring.core.practica9.beandefinitioninheritance.bean.ConnectionDataBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanDefinitionInheritanceTest {

	@Test
	public void beanDefinitionInheritanceTest1() {

		log.info("beanDefinitionInheritanceTest1 -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica9/bean-def-inheritance-application-context.xml");

		ConnectionDataBase conexionProduccion = applicationContext.getBean(
				"conexionProduccionBean", ConnectionDataBase.class);

		Assert.assertNotNull(conexionProduccion);

		conexionProduccion.showInfo();

		ConnectionDataBase conexionPruebas = applicationContext.getBean(
				"conexionPruebasBean", ConnectionDataBase.class);

		Assert.assertNotNull(conexionPruebas);

		conexionPruebas.showInfo();

		((AbstractApplicationContext) applicationContext).close();
	}

	@Test
	public void beanDefinitionInheritanceTest2() {

		log.info("beanDefinitionInheritanceTest2 -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica9/bean-def-inheritance-2-application-context.xml");

		ConnectionDataBase conexionProduccion = applicationContext.getBean(
				"conexionProduccionBean", ConnectionDataBase.class);

		Assert.assertNotNull(conexionProduccion);

		conexionProduccion.showInfo();

		ConnectionDataBase conexionPruebas = applicationContext.getBean(
				"conexionPruebasBean", ConnectionDataBase.class);

		Assert.assertNotNull(conexionPruebas);

		conexionPruebas.showInfo();

		((AbstractApplicationContext) applicationContext).close();
	}

}

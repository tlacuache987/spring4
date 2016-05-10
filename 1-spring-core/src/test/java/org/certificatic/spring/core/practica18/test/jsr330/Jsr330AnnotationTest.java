package org.certificatic.spring.core.practica18.test.jsr330;

import javax.annotation.Resource;

import org.certificatic.spring.core.practica18.jsr330.bean.Corporation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/practica18/annotations-jsr330-application-context.xml")
public class Jsr330AnnotationTest {

	@Resource
	private Corporation corporation;

	@Before
	public void beforeClass() {
		Assert.assertNotNull(corporation);
	}

	@Test
	public void jsr330AnnotationTest() {

		log.info("jsr330AnnotationTest -------------------");

		Assert.assertNotNull(corporation);

		log.info("corporation: {}", corporation);
	}

}

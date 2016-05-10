package org.certificatic.spring.aop.practica23.test.aspectjconfig;

import org.certificatic.spring.aop.practica23.aspectjconfig.SpringAspectJAopConfig;
import org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.IVoluntario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringAspectJAopConfig.class })
@ActiveProfiles("shortcut")
public class MagoSpringAopAspectJTest {

	@Autowired
	private IVoluntario voluntario;

	@Before
	public void setUp() {
		Assert.assertNotNull(voluntario);
	}

	@Test
	public void magoSpringAopAspectJTest() {

		log.info("magoSpringAopAspectJTest -------------------");

		boolean joke = false;

		voluntario.pensarEnAlgo("coca-cola");

		String pensamiento = voluntario.getPensamiento(joke);

		String expectedPensamiento = "coca-cola";

		Assert.assertEquals(expectedPensamiento, pensamiento);

		log.info("pensamiento: {}", pensamiento);
		log.info("expectedPensamiento: {}", expectedPensamiento);
	}

	@Test
	public void magoSpringAopAspectJWithJokeTest() {

		log.info("magoSpringAopAspectJWithJokeTest -------------------");

		boolean joke = true;

		voluntario.pensarEnAlgo("coca-cola");

		String pensamiento = voluntario.getPensamiento(joke);

		String expectedPensamiento = "No me he bañado en 5 dias";

		Assert.assertEquals(expectedPensamiento, pensamiento);

		log.info("pensamiento: {}", pensamiento);
		log.info("expectedPensamiento: {}", expectedPensamiento);
	}

	@Test
	public void magoSpringAopAspectJWithJokeTest2() {

		log.info("magoSpringAopAspectJWithJokeTest -------------------");

		boolean joke = true;

		voluntario.pensarEnAlgo("coca-cola");

		String pensamiento = voluntario.getPensamiento(joke);

		String expectedPensamiento = "coca-cola";

		Assert.assertEquals(expectedPensamiento, pensamiento);

		log.info("pensamiento: {}", pensamiento);
		log.info("expectedPensamiento: {}", expectedPensamiento);
	}

}

package org.certificatic.practica1.interfaces.livingbeing.test;

import org.certificatic.practica1.interfaces.livingbeing.LivingBeingType;
import org.certificatic.practica1.interfaces.livingbeing.api.IBugEater;
import org.certificatic.practica1.interfaces.livingbeing.api.ILivingBeing;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.Aardvark;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.VenusFlyTrap;
import org.certificatic.practica1.interfaces.spring.core.Spring;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LivingBeingMejoradoTest {

	@Test
	public void livingBeingTest() {
		log.info("livingBeingTest -------------------");

		ILivingBeing livingBeing1 = Spring.getBean(LivingBeingType.VENUS_FLY_TRAP);
		ILivingBeing livingBeing2 = Spring.getBean(LivingBeingType.AARDVARK);
		
		((IBugEater)livingBeing1).eatBug();
		((IBugEater)livingBeing2).eatBug();
		
		Assert.assertTrue(livingBeing1 instanceof ILivingBeing);
		Assert.assertTrue(livingBeing2 instanceof ILivingBeing);

		Assert.assertTrue(livingBeing1 instanceof VenusFlyTrap);
		Assert.assertTrue(livingBeing2 instanceof Aardvark);
	}

}

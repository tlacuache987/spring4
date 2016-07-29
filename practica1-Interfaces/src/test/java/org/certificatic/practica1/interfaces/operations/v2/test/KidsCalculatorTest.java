package org.certificatic.practica1.interfaces.operations.v2.test;

import java.text.DecimalFormat;

import org.certificatic.practica1.interfaces.operations.v2.api.IKidsCalculator;
import org.certificatic.practica1.interfaces.operations.v2.api.impl.KidsCalculator;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KidsCalculatorTest {

	private DecimalFormat df = new DecimalFormat("#.00##");

	@Test
	public void kidsCalculatorTest() {
		log.info("kidsCalculatorTest -------------------");

		final IKidsCalculator kidsCal = new KidsCalculator();
		final IKidsCalculator kidsCal2 = new KidsCalculator();

		final double expectedValue = 6D;

		kidsCal.set(5).add(5).add(5).add(-5).subtract(4);
		kidsCal2.set(-5).add(5).add(5).subtract(4).add(5);

		log.info("kidsCal.result(): {}", df.format(kidsCal.result()));
		log.info("kidsCal2.result(): {}", df.format(kidsCal2.result()));

		Assert.assertEquals(expectedValue, kidsCal.result(), 0.0001);
		Assert.assertEquals(expectedValue, kidsCal2.result(), 0.0001);

	}
}

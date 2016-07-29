package org.certificatic.practica1.interfaces.operations.v3.test;

import java.text.DecimalFormat;

import org.certificatic.practica1.interfaces.operations.v3.api.ICalculator;
import org.certificatic.practica1.interfaces.operations.v3.api.impl.KidsCalculator;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KidsCalculatorTest {

	private DecimalFormat df = new DecimalFormat("#.00##");

	@Test
	public void kidsCalculatorTest() {
		log.info("kidsCalculatorTest -------------------");

		final ICalculator<?> kidsCal = new KidsCalculator();
		final ICalculator<?> kidsCal2 = new KidsCalculator();

		final double expectedValue = 6D;

		((KidsCalculator) kidsCal).set(5).add(5).add(5).add(-5).subtract(4);
		((KidsCalculator) kidsCal2).set(-5).add(5).add(5).subtract(4).add(5);

		log.info("kidsCal.result(): {}", df.format(kidsCal.result()));
		log.info("kidsCal2.result(): {}", df.format(kidsCal2.result()));

		Assert.assertEquals(expectedValue, kidsCal.result(), 0.0001);
		Assert.assertEquals(expectedValue, kidsCal2.result(), 0.0001);

	}
}
